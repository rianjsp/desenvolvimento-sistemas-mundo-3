-- Consulta para calcular o valor total das entradas agrupadas por produto
SELECT 
    p.Nome AS Produto,
    SUM(m.Quantidade * m.PrecoUnitario) AS ValorTotalEntradas
FROM 
    Movimentos m
JOIN 
    Produtos p ON m.IdProduto = p.Id
WHERE 
    m.Tipo = 'Compra' -- Filtra as compras
GROUP BY 
    p.Nome; -- Agrupa os resultados pelo nome do produto

-- Consulta para calcular o valor total das saídas agrupadas por produto
SELECT 
    p.Nome AS Produto,
    SUM(m.Quantidade * m.PrecoUnitario) AS ValorTotalSaidas
FROM 
    Movimentos m
JOIN 
    Produtos p ON m.IdProduto = p.Id
WHERE 
    m.Tipo = 'Venda' -- Filtra apenas vendas
GROUP BY 
    p.Nome; -- Agrupa os resultados pelo nome do produto

-- Consulta para listar operadores que nao efetuaram movimentacoes de entrada (compra - Entrada)
SELECT 
    u.Nome AS Operador
FROM 
    Usuarios u
LEFT JOIN 
    Movimentos m ON u.Id = m.IdPessoa AND m.Tipo = 'Compra' -- Faz um join com a tabela de movimentos
WHERE 
    m.Id IS NULL; -- Filtra para mostrar apenas operadores sem compras

-- Consulta para calcular o valor total de entradas, agrupado por operador
SELECT 
    u.Nome AS Operador,
    SUM(m.Quantidade * m.PrecoUnitario) AS ValorTotalEntradas
FROM 
    Movimentos m
JOIN 
    Usuarios u ON m.IdPessoa = u.Id
WHERE 
    m.Tipo = 'Compra' -- Filtra apenas as compras
GROUP BY 
    u.Nome; -- Agrupa os resultados pelo nome do operador

-- Consulta para calcular o valor total de saídas, agrupado por operador
SELECT 
    u.Nome AS Operador,
    SUM(m.Quantidade * m.PrecoUnitario) AS ValorTotalSaidas
FROM 
    Movimentos m
JOIN 
    Usuarios u ON m.IdPessoa = u.Id
WHERE 
    m.Tipo = 'Venda' -- Filtra apenas  vendas
GROUP BY 
    u.Nome; -- Agrupa os resultados pelo nome do operador

-- Consulta para calcular o valor medio de venda por produto, utilizando media ponderada
SELECT 
    p.Nome AS Produto,
    SUM(m.Quantidade * m.PrecoUnitario) / SUM(m.Quantidade) AS MediaPonderada
FROM 
    Movimentos m
JOIN 
    Produtos p ON m.IdProduto = p.Id
WHERE 
    m.Tipo = 'Venda' -- Filtra apenas vendas
GROUP BY 
    p.Nome; -- Agrupa os resultados pelo nome do produto
