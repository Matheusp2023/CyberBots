# **📌 Escopo do Projeto – Simulador de Robótica em 3D**  

## **1. Visão Geral**  
O projeto consiste no desenvolvimento de um **Simulador de Robótica em 3D**, permitindo que usuários projetem, testem e otimizem robôs em um ambiente virtual antes de implementá-los no mundo real. Ele será útil para pesquisa, ensino e desenvolvimento de robôs móveis, braços robóticos e drones.  

## **2. Objetivos**  
- Criar um ambiente 3D interativo para simulação de robôs.  
- Permitir a importação de modelos de robôs usando padrões como **URDF** e **SDF**.  
- Simular sensores como **câmeras, LIDAR, IMU e encoders**.  
- Oferecer integração com frameworks populares de robótica, como **ROS2** e **Gazebo**.  
- Suporte para **física realista**, incluindo colisões e atrito.  
- Permitir programação dos robôs via **scripts**.  

## **3. Público-Alvo**  
- Pesquisadores e engenheiros de robótica.  
- Estudantes universitários e professores.  
- Desenvolvedores que trabalham com veículos autônomos e drones.  
- Empresas que desenvolvem hardware robótico.  

## **4. Funcionalidades Principais**  
### **🔹 Ambiente de Simulação 3D**  
- Renderização em **tempo real** com motores gráficos.  
- Interface para **criação e configuração** de cenários e ambientes de testes.  
- **Suporte a terrenos e obstáculos** configuráveis.  

### **🔹 Modelagem e Importação de Robôs**  
- Suporte a arquivos **URDF/SDF** para importar robôs do ROS.  
- Biblioteca de **modelos pré-configurados** (carros autônomos, braços robóticos, drones).  
- Editor gráfico para **montagem personalizada de robôs**.  

### **🔹 Simulação Física**  
- Implementação de física realista.  
- Simulação de **gravidade, colisões e dinâmica de motores**.  
- Controle de **PID para motores** e juntas robóticas.  

### **🔹 Sensores e Percepção**  
- **Simulação de sensores**:  
  - Câmeras RGB e estéreo  
  - LIDAR (simulação de varredura a laser)  
  - IMU (acelerômetro e giroscópio)  
  - Sensores ultrassônicos e infravermelhos  
- Visualização dos dados dos sensores em tempo real.  

### **🔹 Programação e Controle dos Robôs**  
- Interface para **escrita de scripts**.  
- Suporte a **ROS2 para envio e recebimento de comandos**.  
- Controle de **trajetória e navegação autônoma**.  

### **🔹 Exportação e Integração**  
- Exportação de simulações para **arquivos de log CSV, JSON ou ROS bag**.  
- API para integração com **machine learning e visão computacional**.  
- Suporte para simulação remota via **servidor dedicado**.  

## **5. Tecnologias Utilizadas**  
- **Linguagens**: Java
- **Interface GUI**: NetBeans Plataform
