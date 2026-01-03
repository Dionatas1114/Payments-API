-- Tabela de usuários
CREATE TABLE IF NOT EXISTS users (
    id UUID PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(30) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    phone VARCHAR(30) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

-- Tabela de configurações do usuário
CREATE TABLE IF NOT EXISTS user_configurations (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    has_notifications BOOLEAN NOT NULL DEFAULT false,
    language VARCHAR(10) NOT NULL DEFAULT 'pt_BR',
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Tabela de produtos
CREATE TABLE IF NOT EXISTS products (
    id UUID PRIMARY KEY,
    item_name VARCHAR(50) NOT NULL,
    item_category VARCHAR(50) NOT NULL DEFAULT 'Other',
    total_price NUMERIC(10, 2) NOT NULL,
    discount_price NUMERIC(10, 2) NOT NULL DEFAULT 0.00,
    internal_code VARCHAR(50) NOT NULL,
    description TEXT,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

-- Tabela de serviços
CREATE TABLE IF NOT EXISTS services (
    id UUID PRIMARY KEY,
    item_name VARCHAR(50) NOT NULL,
    item_category VARCHAR(50) NOT NULL DEFAULT 'Other',
    total_price NUMERIC(10, 2) NOT NULL,
    discount_price NUMERIC(10, 2) NOT NULL DEFAULT 0.00,
    internal_code VARCHAR(50) NOT NULL,
    description TEXT,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

-- Tabela base para transações
CREATE TABLE IF NOT EXISTS transaction_base (
    id UUID PRIMARY KEY,
    payment_method VARCHAR(50) NOT NULL,
    payment_conditions VARCHAR(100),
    total_value NUMERIC(10, 2) NOT NULL,
    discount_value NUMERIC(10, 2) NOT NULL DEFAULT 0.00,
    additional_costs NUMERIC(10, 2) NOT NULL DEFAULT 0.00,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

-- Tabela de pagamentos
CREATE TABLE IF NOT EXISTS payments (
    id UUID PRIMARY KEY,
    payment_method VARCHAR(50) NOT NULL,
    payment_conditions VARCHAR(100),
    total_value NUMERIC(10, 2) NOT NULL,
    discount_value NUMERIC(10, 2) NOT NULL DEFAULT 0.00,
    additional_costs NUMERIC(10, 2) NOT NULL DEFAULT 0.00,
    description TEXT,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

-- Tabela de recibos
CREATE TABLE IF NOT EXISTS receipts (
    id UUID PRIMARY KEY,
    payment_method VARCHAR(50) NOT NULL,
    payment_conditions VARCHAR(100),
    total_value NUMERIC(10, 2) NOT NULL,
    discount_value NUMERIC(10, 2) NOT NULL DEFAULT 0.00,
    additional_costs NUMERIC(10, 2) NOT NULL DEFAULT 0.00,
    description TEXT,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

-- Índices para melhorar performance
CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_users_phone ON users(phone);
CREATE INDEX idx_products_internal_code ON products(internal_code);
CREATE INDEX idx_services_internal_code ON services(internal_code);
