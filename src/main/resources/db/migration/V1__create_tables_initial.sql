CREATE SEQUENCE user_id_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE TABLE users (
    id BIGINT NOT NULL DEFAULT nextval('user_id_seq'),
    telegram_id BIGINT NOT NULL,
    user_name VARCHAR(255) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,

    CONSTRAINT pk_users PRIMARY KEY (id),
    CONSTRAINT uk_users_telegram_id UNIQUE (telegram_id)
);

ALTER SEQUENCE user_id_seq OWNED BY users.id;


CREATE SEQUENCE subs_id_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE subscriptions (
    id BIGINT PRIMARY KEY DEFAULT nextval('subs_id_seq'),
    user_id BIGINT NOT NULL,
    symbol VARCHAR(50) NOT NULL,
    time_frame VARCHAR(50) NOT NULL,
    active BOOLEAN NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL,

    CONSTRAINT fk_subscription_user
        FOREIGN KEY (user_id)
        REFERENCES users(id),

    CONSTRAINT uk_user_symbol_timeframe
        UNIQUE(user_id, symbol, time_frame)
);

CREATE INDEX idx_subscription_symbol_timeframe
ON subscriptions(symbol, time_frame);