-- Selecionando o banco de dados 'Loja'
USE Loja; 
GO

-- Criaando a tabela de pessoas com ID, nome, endereço e telefone
CREATE TABLE Pessoas (
    Id INT PRIMARY KEY IDENTITY(1,1), 
    Nome VARCHAR(100) NOT NULL, 
    Endereco VARCHAR(255), 
    Telefone VARCHAR(15)
);
GO

-- Tabela pessoasFísicas, referenciando a tabela de pessoas
CREATE TABLE PessoasFisicas (
    Id INT PRIMARY KEY FOREIGN KEY REFERENCES Pessoas(Id), 
    CPF VARCHAR(11) NOT NULL 
);
GO

-- Tabela pessoasFurídicas, referenciando a tabela de pessoas
CREATE TABLE PessoasJuridicas (
    Id INT PRIMARY KEY FOREIGN KEY REFERENCES Pessoas(Id), 
    CNPJ VARCHAR(14) NOT NULL 
);
GO

-- Tabela Usuários do sistema
CREATE TABLE Usuarios (
    Id INT PRIMARY KEY IDENTITY(1,1), 
    Nome VARCHAR(100) NOT NULL, 
    Senha VARCHAR(50) NOT NULL 
);
GO

-- Tabela Produtos com ID, nome, quantidade e preço
CREATE TABLE Produtos (
    Id INT PRIMARY KEY IDENTITY(1,1), 
    Nome VARCHAR(100) NOT NULL, 
    Quantidade INT NOT NULL, 
    Preco DECIMAL(10, 2) NOT NULL 
);
GO

-- Tabela para regstrar movimentações de produtos
CREATE TABLE Movimentos (
    Id INT PRIMARY KEY IDENTITY(1,1), 
    Tipo VARCHAR(10) CHECK (Tipo IN ('Compra', 'Venda')), 
    IdProduto INT FOREIGN KEY REFERENCES Produtos(Id), 
    IdPessoa INT FOREIGN KEY REFERENCES Pessoas(Id), 
    Quantidade INT NOT NULL, 
    PrecoUnitario DECIMAL(10, 2) NOT NULL, 
    DataMovimento DATETIME DEFAULT GETDATE() 
);
GO

-- Adicionando o Sequence para os IDs de pessoas
CREATE SEQUENCE SeqPessoa 
    START WITH 1 
    INCREMENT BY 1 
    NO MAXVALUE 
    CACHE 10; 
GO

-- Inicia uma transação para garantir a integridade dos dados
BEGIN TRANSACTION; 

-- Declaração de variáveis para armazenar IDs
DECLARE @IdPessoaFisica INT; 
DECLARE @IdPessoaJuridica INT; 

-- Exemplos Somente para início
INSERT INTO Usuarios (Nome, Senha) VALUES ('Operador 1', 'senha123'); 

INSERT INTO Pessoas (Nome, Endereco, Telefone) 
VALUES ('Algusto Nascimento', 'Rua Adalberto, 123', '123456789'); 
SET @IdPessoaFisica = SCOPE_IDENTITY(); 
INSERT INTO PessoasFisicas (Id, CPF) VALUES (@IdPessoaFisica, '12345678901'); 

INSERT INTO Pessoas (Nome, Endereco, Telefone) 
VALUES ('Sempre Vida', 'Avenida Olinda, 456', '987654321'); 
SET @IdPessoaJuridica = SCOPE_IDENTITY(); 
INSERT INTO PessoasJuridicas (Id, CNPJ) VALUES (@IdPessoaJuridica, '12345678000195'); 

INSERT INTO Produtos (Nome, Quantidade, Preco) 
VALUES ('Shampoo', 100, 10.50); 

-- Movimentações como exemplo
INSERT INTO Movimentos (Tipo, IdProduto, IdPessoa, Quantidade, PrecoUnitario) 
VALUES ('Compra', 1, @IdPessoaJuridica, 10, 10.00); 


INSERT INTO Movimentos (Tipo, IdProduto, IdPessoa, Quantidade, PrecoUnitario) 
VALUES ('Venda', 1, @IdPessoaFisica, 5, 10.50); 


COMMIT TRANSACTION; 
