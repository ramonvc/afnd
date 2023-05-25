# Simulador de Autômatos Finitos Não Determinísticos (AFND) 🤖  
  
Este projeto é um simulador de AFND que permite aos usuários definir estados, estados finais, estado inicial, transições e fornecer cadeias de entrada para verificar se são aceitas ou não pelo autômato. O simulador foi desenvolvido em Java.  
  
## 🛠️ Como usar 🛠️  
  
1. Compile e execute o arquivo `App.java`.  
2. Siga as instruções exibidas no terminal para inserir estados, estados finais, estado inicial e transições.  
3. Insira as cadeias de entrada para verificar se são aceitas ou recusadas pelo autômato.  
  
## 📚 Exemplos de testes manuais 📚  
  
### Cenário 1  
  
Descrição do AFND:  
- Estados: q0, q1, q2  
- Estado inicial: q0  
- Estados finais: q2  
- Transições:  
  - (q0, q1, a)  
  - (q1, q2, b)  
  - (q0, q2, a)  
  
Entradas e saídas esperadas:  
- Entrada: "ab" -> Saída: ACEITA ✅  
- Entrada: "a" -> Saída: ACEITA ✅  
- Entrada: "b" -> Saída: RECUSA ❌  
- Entrada: "aa" -> Saída: RECUSA ❌  
- Entrada: "bb" -> Saída: RECUSA ❌  
- Entrada: "ba" -> Saída: RECUSA ❌  
  
### Cenário 2  
  
Descrição do AFND:  
- Estados: p0, p1, p2  
- Estado inicial: p0  
- Estados finais: p2  
- Transições:  
  - (p0, p1, a)  
  - (p1, p1, b)  
  - (p1, p2, c)  
  - (p0, p2, a)  
  
Entradas e saídas esperadas:  
- Entrada: "a" -> Saída: ACEITA ✅  
- Entrada: "ab" -> Saída: RECUSA ❌  
- Entrada: "abc" -> Saída: ACEITA ✅  
- Entrada: "ac" -> Saída: ACEITA ✅  
- Entrada: "bb" -> Saída: RECUSA ❌  
- Entrada: "cc" -> Saída: RECUSA ❌  
