# Vitorioso Auxílios — Android + API

Este projeto mantém **sem modificações** os arquivos visuais do ZIP original em `android/app/src/main/assets/`. O APK abre esse painel num WebView, disponibiliza um botão para iniciar o pacote `com.dts.freefiremax` (quando instalado) e um menu flutuante do Android com botões para abrir o painel/jogo e fechar o menu.

## Compilar APK
Abra a pasta `android/` no Android Studio com JDK 17 e Android SDK 35, sincronize o Gradle e use **Build > Build APK(s)**. O projeto contém o código-fonte, **não** um APK compilado ou testado em aparelho. Android 8+ é necessário. A permissão de sobreposição deve ser concedida manualmente; versões recentes do Android podem restringir sobreposições. A compilação depende de downloads Gradle/SDK.

## API
Na pasta `api/`, configure `JWT_SECRET` com um segredo longo e execute `node server.js` com Node.js 18+. Endpoints: `GET /api/status`, `POST /api/register` com `{ "email":"...", "password":"..." }`, `POST /api/login` e `GET /api/me` com header `Authorization: Bearer TOKEN`. Para produção, use HTTPS, banco de dados real, rate limiting e política de privacidade; o armazenamento JSON local é apenas um protótipo. **A API não está conectada à interface original nem hospedada**, e não cria automaticamente uma conta de dono.

## Limites
O menu não lê memória, não desenha ESP sobre jogadores reais e não altera a mira do Free Fire. Não há integração autorizada com o jogo. O site arquivado pode depender de serviços externos que não estão presentes, então algumas funções originais podem não funcionar offline. A aparência foi preservada nos arquivos originais; não há garantia de que todos os recursos externos carreguem.


## Novo: Treinamento Aim + ESP
No aplicativo, toque em **Treinar Aim + ESP**. A tela de treinamento possui alvos animados, seleção do alvo dentro do FOV, suavização de mira, ESP Box, ESP Line, ESP Antena e ESP Distância. Os controles ativam/desativam as visualizações **somente nessa arena independente**. O menu flutuante também oferece acesso ao treino.

**Não é um cheat para Free Fire:** não lê dados do jogo, não identifica jogadores reais e não injeta comandos de mira. O botão Abrir FF MAX apenas tenta iniciar o aplicativo instalado.

O projeto é código-fonte; requer Android SDK/Gradle para gerar APK. A API precisa de hospedagem própria e configuração segura antes de uso público.
