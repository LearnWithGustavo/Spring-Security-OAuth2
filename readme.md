# Tutorial de Autenticação OAuth2 com Google

Para este tutorial, é necessário ter uma conta no **GCP (Google Cloud Platform)** para criar os tokens necessários para execução do OAuth2.

---

## Como gerar o Client ID e Client Secret para OAuth2

1. Acesse o Console do GCP: [https://console.cloud.google.com/](https://console.cloud.google.com/)
2. Vá em **Menu → API & Services → Credentials**.
   > Pode ser que o GCP solicite criar uma tela de consentimento. Para isso:
  - Vá em **Menu → API & Services → OAuth consent screen → Branding**
  - Insira os dados básicos para prosseguir com a criação do consentimento.

3. Em **OAuth consent screen → Audience**, defina como **External**.

4. Após configurar o consentimento, criaremos a credencial OAuth Client ID:
  - Dentro de **Credentials**, clique em **Create Credentials → OAuth Client ID**.
  - Preencha as informações:
    - **Tipo de aplicativo**: `Web application`
    - **Nome**: Nome da sua aplicação
    - **URI de redirecionamento**:
      ```
      http://localhost:8080/login/oauth2/code/google
      ```
      > Detalhe: este é o caminho da rota que será usado para autenticação.  
      > Para projetos em produção, use a URI compatível com seu ambiente.  
      > O caminho `/login/oauth2/code/google` já é tratado pelo Spring Security.

5. Clique em **Create**. Uma tela exibirá suas chaves **Client ID** e **Client Secret**.
   > **Importante:** após fechar essa tela, você não conseguirá visualizar novamente essas chaves.  
   > **Não compartilhe essas chaves.**

---

## Configuração no `application.properties`

Adicione suas chaves do Google:

```
# Adaptador de conexão para autenticação
spring.security.oauth2.client.registration.google.client-id={{SEU-CLIENT-ID}}
spring.security.oauth2.client.registration.google.client-secret={{SEU-CLIENT-SECRET}}
```

Curiosidade: caso queira usar outro serviço de OAuth2, basta trocar a registration no application.properties:

````
spring.security.oauth2.client.registration.<servico-desejado>.client-id={{CLIENT-ID}}
````

## Como usar JWT pelo Google
Ao gerar seu JWT pela rota /private, você pode acessar a classe SecurityConfig e descomentar a linha comentada.
Isso permite autenticação apenas via token JWT.

**Linha comentada:**
```
.oauth2Login(Customizer.withDefaults()).oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))  
```

**Descomente essa linha tambem, dentro do ```application.proprieties```:**
```
spring.security.oauth2.resourceserver.jwt.issuer-uri=https://accounts.google.com
```
Ela é responsavel por permitir que o google receba o JWT para validar para voce se o token é valido


Exemplos de requisições para teste estão localizados em ```oauth2/requests```.

**Curiosidade**: você pode decodificar e visualizar os dados do seu JWT em https://www.jwt.io/


