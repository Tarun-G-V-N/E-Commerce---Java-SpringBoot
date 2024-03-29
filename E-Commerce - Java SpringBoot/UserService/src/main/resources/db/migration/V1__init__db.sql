CREATE TABLE ecom_user
(
    id       BIGINT AUTO_INCREMENT NOT NULL,
    email    VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    CONSTRAINT pk_ecomuser PRIMARY KEY (id)
);

CREATE TABLE ecom_user_roles
(
    ecom_user_id BIGINT NOT NULL,
    roles_id     BIGINT NOT NULL,
    CONSTRAINT pk_ecomuser_roles PRIMARY KEY (ecom_user_id, roles_id)
);

CREATE TABLE `role`
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    role_name VARCHAR(255) NULL,
    CONSTRAINT pk_role PRIMARY KEY (id)
);

CREATE TABLE session
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    user_id        BIGINT NULL,
    token          VARCHAR(255) NULL,
    login_at       datetime NULL,
    expire_at      datetime NULL,
    session_status SMALLINT NULL,
    CONSTRAINT pk_session PRIMARY KEY (id)
);

ALTER TABLE session
    ADD CONSTRAINT FK_SESSION_ON_USER FOREIGN KEY (user_id) REFERENCES ecom_user (id);

ALTER TABLE ecom_user_roles
    ADD CONSTRAINT fk_ecouserol_on_role FOREIGN KEY (roles_id) REFERENCES `role` (id);

ALTER TABLE ecom_user_roles
    ADD CONSTRAINT fk_ecouserol_on_user FOREIGN KEY (ecom_user_id) REFERENCES ecom_user (id);