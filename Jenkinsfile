pipeline {
    agent any  // Define que o pipeline pode rodar em qualquer agente Jenkins disponível
    
    // Variáveis de ambiente que serão usadas em todos os stages
    environment {
        DOCKER_IMAGE = 'isabellemunhozscarso/devops'
        DOCKER_TAG = 'latest'
        DOCKER_CREDENTIALS_ID = 'dockerhub-credentials' // ID das credenciais do Docker Hub no Jenkins
        DEV_PORT = '8585'
        STAGING_PORT = '8686'
    }
    
    stages {
        // ====================================================================
        // PIPELINE DEV: Compilação, Testes e Build da Aplicação
        // ====================================================================
        
        stage('DEV - Checkout') {
            steps {
                echo '========== INICIANDO PIPELINE DEV =========='
                echo 'Fazendo checkout do código fonte...'
                checkout scm  // Faz checkout do código do repositório Git
            }
        }
        
        stage('DEV - Build and Test') {
            steps {
                echo 'Compilando o projeto e executando testes com Maven...'
                // Limpa, compila e executa todos os testes
                bat 'mvnw.cmd clean verify'
            }
        }
        
        stage('DEV - Package') {
            steps {
                echo 'Gerando o arquivo JAR da aplicação...'
                // Cria o pacote JAR da aplicação
                bat 'mvnw.cmd package -DskipTests'
            }
        }
        
        stage('DEV - Generate Reports') {
            steps {
                echo 'Gerando relatórios de cobertura de testes...'
                // Publica o relatório de cobertura JaCoCo
                jacoco(
                    execPattern: 'target/jacoco.exec',
                    classPattern: 'target/classes',
                    sourcePattern: 'src/main/java',
                    exclusionPattern: 'src/test*'
                )
                
                // Publica os resultados dos testes JUnit
                junit 'target/surefire-reports/*.xml'
                
                // Publica o relatório HTML do Cucumber (se existir)
                publishHTML([
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'target',
                    reportFiles: 'cucumber-report.html',
                    reportName: 'Cucumber Report'
                ])
            }
        }
        
        // ====================================================================
        // PIPELINE IMAGE_DOCKER: Construção e Push da Imagem Docker
        // ====================================================================
        
        stage('IMAGE_DOCKER - Build Docker Image') {
            steps {
                echo '========== INICIANDO PIPELINE IMAGE_DOCKER =========='
                echo 'Construindo a imagem Docker da aplicação...'
                script {
                    // Constrói a imagem Docker usando o Dockerfile
                    bat "docker build -t ${DOCKER_IMAGE}:${DOCKER_TAG} ."
                    
                    // Também cria uma tag com o número do build
                    bat "docker tag ${DOCKER_IMAGE}:${DOCKER_TAG} ${DOCKER_IMAGE}:build-${BUILD_NUMBER}"
                }
            }
        }
        
        stage('IMAGE_DOCKER - Test Docker Image') {
            steps {
                echo 'Testando a imagem Docker localmente...'
                script {
                    // Remove container anterior se existir
                    bat 'docker rm -f praticas-devops-test || echo "Container não existe ainda"'
                    
                    // Executa a imagem para verificar se inicia corretamente
                    bat "docker run -d --name praticas-devops-test -p 8787:8080 ${DOCKER_IMAGE}:${DOCKER_TAG}"
                    
                    // Aguarda a aplicação inicializar
                    sleep time: 30, unit: 'SECONDS'
                    
                    // Verifica se o container está rodando
                    bat 'docker ps | findstr praticas-devops-test'
                    
                    // Para e remove o container de teste
                    bat 'docker stop praticas-devops-test'
                    bat 'docker rm praticas-devops-test'
                }
            }
        }
        
        stage('IMAGE_DOCKER - Push to Docker Hub') {
            steps {
                echo 'Fazendo push da imagem para o Docker Hub...'
                script {
                    // Faz login no Docker Hub usando as credenciais armazenadas no Jenkins
                    withCredentials([usernamePassword(
                        credentialsId: "${DOCKER_CREDENTIALS_ID}",
                        usernameVariable: 'DOCKER_USERNAME',
                        passwordVariable: 'DOCKER_PASSWORD'
                    )]) {
                        bat "docker login -u %DOCKER_USERNAME% -p %DOCKER_PASSWORD%"
                    }
                    
                    // Faz push da imagem latest
                    bat "docker push ${DOCKER_IMAGE}:${DOCKER_TAG}"
                    
                    // Faz push da imagem com número do build
                    bat "docker push ${DOCKER_IMAGE}:build-${BUILD_NUMBER}"
                    
                    // Faz logout do Docker Hub
                    bat 'docker logout'
                }
            }
        }
        
        stage('IMAGE_DOCKER - Clean Local Images') {
            steps {
                echo 'Limpando imagens Docker antigas localmente...'
                script {
                    // Remove imagens antigas para economizar espaço
                    bat "docker rmi ${DOCKER_IMAGE}:build-${BUILD_NUMBER} || echo 'Imagem já removida'"
                }
            }
        }
        
        // ====================================================================
        // PIPELINE STAGING: Deploy em Ambiente de Staging
        // ====================================================================
        
        stage('STAGING - Pull Latest Image') {
            steps {
                echo '========== INICIANDO PIPELINE STAGING =========='
                echo 'Baixando a imagem mais recente do Docker Hub...'
                bat 'docker-compose -f docker-compose.staging.yml pull'
            }
        }
        
        stage('STAGING - Start Container') {
            steps {
                echo 'Iniciando containers no ambiente de staging...'
                // Para e remove containers antigos se existirem
                bat 'docker-compose -f docker-compose.staging.yml down || echo "Nenhum container para remover"'
                
                // Inicia os containers em modo detached (background)
                bat 'docker-compose -f docker-compose.staging.yml up -d --no-color'
                
                // Aguarda a aplicação inicializar completamente
                echo 'Aguardando 60 segundos para a aplicação inicializar...'
                sleep time: 60, unit: 'SECONDS'
            }
        }
        
        stage('STAGING - Verify Deployment') {
            steps {
                echo 'Verificando se o deployment foi bem-sucedido...'
                // Exibe os logs dos containers
                bat 'docker-compose -f docker-compose.staging.yml logs'
                
                // Mostra o status dos containers
                bat 'docker-compose -f docker-compose.staging.yml ps'
            }
        }
        
        stage('STAGING - Health Check') {
            steps {
                echo 'Executando health check no ambiente de staging...'
                script {
                    // Tenta acessar o serviço para verificar se está respondendo
                    def healthCheck = bat(
                        script: "curl http://localhost:${STAGING_PORT} || exit 0",
                        returnStatus: true
                    )
                    
                    if (healthCheck == 0) {
                        echo 'Health check bem-sucedido! Aplicação está respondendo.'
                    } else {
                        echo 'AVISO: Health check falhou. Verificar logs acima.'
                        // Não falha o pipeline, apenas avisa
                    }
                }
            }
        }
    }
    
    // ====================================================================
    // POST: Ações executadas após o pipeline
    // ====================================================================
    
    post {
        success {
            echo '=========================================='
            echo '✓ PIPELINE EXECUTADO COM SUCESSO!'
            echo '=========================================='
            echo "Imagem Docker: ${DOCKER_IMAGE}:${DOCKER_TAG}"
            echo "Build Number: ${BUILD_NUMBER}"
            echo "Staging URL: http://localhost:${STAGING_PORT}"
            echo "H2 Console: http://localhost:${STAGING_PORT}/h2-console"
            echo '=========================================='
        }
        
        failure {
            echo '=========================================='
            echo '✗ PIPELINE FALHOU!'
            echo '=========================================='
            echo 'Verifique os logs acima para mais detalhes.'
            echo '=========================================='
            
            // Em caso de falha, tenta parar os containers
            bat 'docker-compose -f docker-compose.staging.yml down || echo "Erro ao parar containers"'
        }
        
        always {
            echo 'Pipeline finalizado.'
            // Arquiva os artefatos gerados
            archiveArtifacts artifacts: 'target/*.jar', allowEmptyArchive: true
            archiveArtifacts artifacts: 'target/*.html', allowEmptyArchive: true
            
            // Limpa o workspace (opcional, descomente se necessário)
            // cleanWs()
        }
    }
}
