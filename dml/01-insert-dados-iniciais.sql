-- =====================================================
-- PRODUTOS
-- =====================================================

INSERT INTO produto (sku, nome, ativo)
VALUES
    ('MOUSE-001', 'Mouse', TRUE),
    ('NOTEBOOK-001', 'Notebook', TRUE),
    ('MONITOR-LG-27', 'Monitor LG 27"', TRUE),
    ('MOUSE-LOGITECH-MX3', 'Mouse Logitech MX Master 3', TRUE),
    ('TECLADO-KEYCHRON-K2', 'Teclado Mecânico Keychron K2', TRUE),
    ('HEADSET-HYPERX-CLOUD2', 'Headset HyperX Cloud II', TRUE),
    ('SSD-KINGSTON-1TB', 'SSD Kingston 1TB NVMe', TRUE),
    ('WEBCAM-LOGITECH-C920', 'Webcam Logitech C920', TRUE),
    ('DOCK-DELL-USBC', 'Dock USB-C Dell', TRUE),
    ('NOTEBOOK-LENOVO-THINKPAD', 'Notebook Lenovo ThinkPad', TRUE);


-- =====================================================
-- ESTOQUES INICIAIS
-- =====================================================

INSERT INTO estoque (
    produto_id,
    quantidade_disponivel,
    quantidade_reservada
)
SELECT id, 50, 0
FROM produto
WHERE sku = 'MOUSE-001';

INSERT INTO estoque (
    produto_id,
    quantidade_disponivel,
    quantidade_reservada
)
SELECT id, 10, 0
FROM produto
WHERE sku = 'NOTEBOOK-001';

INSERT INTO estoque (
    produto_id,
    quantidade_disponivel,
    quantidade_reservada
)
SELECT id, 15, 0
FROM produto
WHERE sku = 'MONITOR-LG-27';

INSERT INTO estoque (
    produto_id,
    quantidade_disponivel,
    quantidade_reservada
)
SELECT id, 20, 0
FROM produto
WHERE sku = 'MOUSE-LOGITECH-MX3';

INSERT INTO estoque (
    produto_id,
    quantidade_disponivel,
    quantidade_reservada
)
SELECT id, 12, 0
FROM produto
WHERE sku = 'TECLADO-KEYCHRON-K2';

INSERT INTO estoque (
    produto_id,
    quantidade_disponivel,
    quantidade_reservada
)
SELECT id, 18, 0
FROM produto
WHERE sku = 'HEADSET-HYPERX-CLOUD2';

INSERT INTO estoque (
    produto_id,
    quantidade_disponivel,
    quantidade_reservada
)
SELECT id, 25, 0
FROM produto
WHERE sku = 'SSD-KINGSTON-1TB';

INSERT INTO estoque (
    produto_id,
    quantidade_disponivel,
    quantidade_reservada
)
SELECT id, 14, 0
FROM produto
WHERE sku = 'WEBCAM-LOGITECH-C920';

INSERT INTO estoque (
    produto_id,
    quantidade_disponivel,
    quantidade_reservada
)
SELECT id, 8, 0
FROM produto
WHERE sku = 'DOCK-DELL-USBC';

INSERT INTO estoque (
    produto_id,
    quantidade_disponivel,
    quantidade_reservada
)
SELECT id, 6, 0
FROM produto
WHERE sku = 'NOTEBOOK-LENOVO-THINKPAD';


-- =====================================================
-- MOVIMENTAÇÕES INICIAIS
-- =====================================================

INSERT INTO movimentacao_estoque (
    produto_id,
    tipo,
    quantidade,
    referencia,
    observacao
)
SELECT
    id,
    'ENTRADA',
    50,
    'CARGA-INICIAL',
    'Carga inicial do estoque'
FROM produto
WHERE sku = 'MOUSE-001';

INSERT INTO movimentacao_estoque (
    produto_id,
    tipo,
    quantidade,
    referencia,
    observacao
)
SELECT
    id,
    'ENTRADA',
    10,
    'CARGA-INICIAL',
    'Carga inicial do estoque'
FROM produto
WHERE sku = 'NOTEBOOK-001';

INSERT INTO movimentacao_estoque (
    produto_id,
    tipo,
    quantidade,
    referencia,
    observacao
)
SELECT
    id,
    'ENTRADA',
    15,
    'CARGA-INICIAL',
    'Carga inicial do estoque'
FROM produto
WHERE sku = 'MONITOR-LG-27';

INSERT INTO movimentacao_estoque (
    produto_id,
    tipo,
    quantidade,
    referencia,
    observacao
)
SELECT
    id,
    'ENTRADA',
    20,
    'CARGA-INICIAL',
    'Carga inicial do estoque'
FROM produto
WHERE sku = 'MOUSE-LOGITECH-MX3';

INSERT INTO movimentacao_estoque (
    produto_id,
    tipo,
    quantidade,
    referencia,
    observacao
)
SELECT
    id,
    'ENTRADA',
    12,
    'CARGA-INICIAL',
    'Carga inicial do estoque'
FROM produto
WHERE sku = 'TECLADO-KEYCHRON-K2';

INSERT INTO movimentacao_estoque (
    produto_id,
    tipo,
    quantidade,
    referencia,
    observacao
)
SELECT
    id,
    'ENTRADA',
    18,
    'CARGA-INICIAL',
    'Carga inicial do estoque'
FROM produto
WHERE sku = 'HEADSET-HYPERX-CLOUD2';

INSERT INTO movimentacao_estoque (
    produto_id,
    tipo,
    quantidade,
    referencia,
    observacao
)
SELECT
    id,
    'ENTRADA',
    25,
    'CARGA-INICIAL',
    'Carga inicial do estoque'
FROM produto
WHERE sku = 'SSD-KINGSTON-1TB';

INSERT INTO movimentacao_estoque (
    produto_id,
    tipo,
    quantidade,
    referencia,
    observacao
)
SELECT
    id,
    'ENTRADA',
    14,
    'CARGA-INICIAL',
    'Carga inicial do estoque'
FROM produto
WHERE sku = 'WEBCAM-LOGITECH-C920';

INSERT INTO movimentacao_estoque (
    produto_id,
    tipo,
    quantidade,
    referencia,
    observacao
)
SELECT
    id,
    'ENTRADA',
    8,
    'CARGA-INICIAL',
    'Carga inicial do estoque'
FROM produto
WHERE sku = 'DOCK-DELL-USBC';

INSERT INTO movimentacao_estoque (
    produto_id,
    tipo,
    quantidade,
    referencia,
    observacao
)
SELECT
    id,
    'ENTRADA',
    6,
    'CARGA-INICIAL',
    'Carga inicial do estoque'
FROM produto
WHERE sku = 'NOTEBOOK-LENOVO-THINKPAD';


-- =====================================================
-- CONSULTA DE CONFERÊNCIA
-- =====================================================

SELECT
    p.id,
    p.sku,
    p.nome,
    p.ativo,
    e.quantidade_disponivel,
    e.quantidade_reservada,
    e.data_atualizacao
FROM produto p
         JOIN estoque e
              ON e.produto_id = p.id
ORDER BY p.id;