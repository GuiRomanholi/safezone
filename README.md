# SafeZone API – FIAP Global (Java)

**SafeZone** é uma solução IoT desenvolvida para ajudar a combater **desastres naturais**, com o objetivo de fazer leituras periodicas sobre a temperatura e a umidade, gerando alertas com nível de criticidade e uma descrição explicativa.

## 🔍 Sobre o Projeto
A dinâmica do SafeZone é simples e eficiente: sensores de campo são instalados com identificação única e coordenadas geográficas precisas. Eles capturam periodicamente informações como temperatura, umidade e localização, enviando os dados para um servidor central.

Essas informações são armazenadas em uma base de dados estruturada, permitindo tanto a análise em tempo real quanto o acesso ao histórico de variações ambientais. Em situações críticas, como alta temperatura ou umidade extremamente baixa, o sistema gera automaticamente alertas com nível de criticidade e descrição explicativa, auxiliando na rápida tomada de decisões.

## 📱 Funcionalidades

- ✅ Monitoramento ambiental contínuo via sensores IoT  
- 🌡️ Coleta de dados como temperatura, umidade e localização geográfica  
- 📦 Armazenamento estruturado e análise histórica de dados  
- 🚨 Geração automática de alertas em situações de risco  
- 🌍 Visualização de informações em **mapas interativos** e **gráficos dinâmicos**  
- 🔐 Controle de acesso para usuários e administradores via painel web  
- 🔋 Sensores otimizados para ambientes remotos com **energia solar** e transmissão em intervalos programados  

## 🔗 Rotas Pricipais (Swagger)

A API do projeto pode ser acessada via localhost no Swagger na rota:

[http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui/index.html)

A API do projeto deployado via Swagger na rota:

[http://52.168.182.169:8081/swagger-ui.html](http://52.168.182.169:8081/swagger-ui/index.html)

## ⚠️ Atenção Importante

Devido aos relacionamentos entre **Dispositivos**, **Leituras** e **Alertas**, **é necessário criar um Dispositivo antes de cadastrar uma Leitura**. A leitura precisa estar vinculada a um dispositivo já registrado no sistema.

Além disso, **os Alertas só são gerados a partir das Leituras**. Portanto, para que um Alerta seja criado, é indispensável que exista pelo menos uma Leitura registrada.


## 🔗 Rotas principais:

### 🚨 Alertas
- `GET /alertas` – Lista todos os alertas  
- `POST /alertas` – Cadastra um novo alerta  
- `GET /alertas/{id}` – Detalhes de um alerta específico  
- `DELETE /alertas/{id}` – Remove um alerta  
- `PUT /alertas/{id}` – Atualiza um alerta existente

---

### 👤 Usuários
- `GET /usuario` – Lista de usuários  
- `POST /auth/register` – Cadastro de usuário  
- `GET /usuario/{id}` – Detalhes de um usuário  
- `DELETE /usuario/{id}` – Remove um usuário
- `PUT /usuario/{id}` – Alterar um usuário 

---

### 📡 Dispositivos
- `GET /dispositivos` – Lista todos os dispositivos  
- `POST /dispositivos` – Cadastra um novo dispositivo  
- `GET /dispositivos/{id}` – Detalhes de um dispositivo específico  
- `DELETE /dispositivos/{id}` – Remove um dispositivo  
- `PUT /dispositivos/{id}` – Atualiza um dispositivo existente  

---

### 📈 Leituras
- `GET /leituras` – Lista todas as leituras  
- `POST /leituras` – Cadastra uma nova leitura  
- `GET /leituras/{id}` – Detalhes de uma leitura específica  
- `DELETE /leituras/{id}` – Remove uma leitura  
- `PUT /leituras/{id}` – Atualiza uma leitura existente  

## Rotas recomendadas para o Teste:
- `POST /auth/register` – Cadastro de usuário
  ```json
  {
   "email": "robertolima@gmail.com",
   "senha": "Roberto123",
   "role": "ADMIN"
  }
- Agora no `/auth/login` coloque seu email e senha e valide o token
- `GET /usuario` – Lista de usuários
- `GET /usuario/{id}` – Detalhes de um usuário
- `DELETE /usuario/{id}` – Remove um usuário
- `PUT /usuario/{id}` – Alterar um usuário
  ```json
  {
   "email": "julicesar@gmail.com",
   "senha": "Julio123",
   "role": "ADMIN"
  }
---
- `POST /dispositivos` – Cadastro de dispositivos  
  ```json
  {
    "latitude": -22.9,
    "longitude": -43.2,
    "localDesc": "Sensor – trilha norte",
    "ativo": true
  }
- `GET /dispositivos` – Lista dispositivos 
- `GET /dispositivos/{id}` – Detalhes de um dispositivo 
- `DELETE /dispositivos/{id}` – Remove um dispositivos 
- `PUT /dispositivos/{id}` – Alterar um dispositivos 
  ```json
  {
    "latitude": 32.9,
    "longitude": -555.2,
    "localDesc": "Sensor – trilha sul",
    "ativo": false
  }
---
- `POST /leituras` – Cadastro de leitura  
  ```json
  {
    "dataHora": "2020-12-08T08:54:25.897Z",
    "temperatura": 19.1,
    "umidade": 121,
    "dispositivoId": 6
  }
- `GET /leituras` – Lista de leituras
- `GET /leituras/{id}` – Detalhes de uma leitura
- `DELETE /leituras/{id}` – Remove uma leitura
- `PUT /leituras/{id}` – Alterar uma leitura
  ```json
  {
    "dataHora": "2025-06-08T08:54:25.897Z",
    "temperatura": 32.1,
    "umidade": 41.1,
    "dispositivoId": 6
  }
# ⚠️ Atenção
Se você for alterar o usuario que está logado no momento terá que logar de novo para assim gerar um novo token para validar de novo.


## 🛠️ Tecnologias Utilizadas

- ☕ Java 17
- 🌱 Spring Boot
- 🟦 Spring Data JPA
- 🟩 Bean Validation
- 📦 Spring Cache
- 📄 Swagger/OpenAPI
- 🛢️ Banco de Dados Oracle
- 🔐 JWT (JSON Web Token) – Autenticação e Autorização
- 🐳 Docker (containerização da API)

## 🚀 Como Executar

1. Clone o repositório:
   ```bash
   git clone https://github.com/GuiRomanholi/safezone.git
   cd safezone

## 🧑‍💻 Integrantes do Grupo

- **Guilherme Romanholi Santos - RM557462**
- **Murilo Capristo - RM556794**
- **Nicolas Guinante Cavalcanti - RM557844**

---

# 🐳 Parte 2: DevOps — Containerização da API

## 🎥 Link do Vídeo
[Link do Video](https://www.youtube.com/watch?v=B6S2-qtuHmQ)

**SafeZone** é uma solução IoT desenvolvida para ajudar a combater **desastres naturais**, com o objetivo de fazer leituras periodicas sobre a temperatura e a umidade, gerando alertas com nível de criticidade e uma descrição explicativa.

## 🔍 Sobre o Projeto
A dinâmica do SafeZone é simples e eficiente: sensores de campo são instalados com identificação única e coordenadas geográficas precisas. Eles capturam periodicamente informações como temperatura, umidade e localização, enviando os dados para um servidor central.

Essas informações são armazenadas em uma base de dados estruturada, permitindo tanto a análise em tempo real quanto o acesso ao histórico de variações ambientais. Em situações críticas, como alta temperatura ou umidade extremamente baixa, o sistema gera automaticamente alertas com nível de criticidade e descrição explicativa, auxiliando na rápida tomada de decisões.

## 📱 Funcionalidades

- ✅ Monitoramento ambiental contínuo via sensores IoT  
- 🌡️ Coleta de dados como temperatura, umidade e localização geográfica  
- 📦 Armazenamento estruturado e análise histórica de dados  
- 🚨 Geração automática de alertas em situações de risco  
- 🌍 Visualização de informações em **mapas interativos** e **gráficos dinâmicos**  
- 🔐 Controle de acesso para usuários e administradores via painel web  
- 🔋 Sensores otimizados para ambientes remotos com **energia solar** e transmissão em intervalos programados 

### Build e execução

1. **Gere o JAR:**
    ```sh
    gradlew build
    ```

2. **Construa a imagem Docker:**
    ```sh
    docker build -t safezone-app .
    ```

3. **Rode o container:**
    ```sh
    docker run -d -p 8081:8081 --name safezone-app safezone-app
    ```

4. **Acesse a aplicação deployada via Swagger:**
    [http://52.168.182.169:8081/swagger-ui.html](http://52.168.182.169:8081/swagger-ui/index.html)

---
