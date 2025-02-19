--database
CREATE SCHEMA craft_verse;

USE craft_verse;

--category table

CREATE TABLE category (
    category_id VARCHAR(255) PRIMARY KEY,      
    category_title VARCHAR(100) NOT NULL,                     
    category_description TEXT, 
    category_image_url TEXT NULL,                         
    category_status ENUM('ACTIVE', 'INACTIVE') DEFAULT 'ACTIVE',
    created_by VARCHAR(255),                                 
    updated_by VARCHAR(255),                                 
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP      
);

--product table

CREATE TABLE product (
    product_id VARCHAR(255) PRIMARY KEY ,
    product_title VARCHAR(255) NOT NULL,
    product_description TEXT,
    product_price DOUBLE NOT NULL,
    product_stock_quantity INT DEFAULT 0,
    category_id VARCHAR(255) PRIMARY KEY,
    product_tags TEXT,
    product_image_url TEXT NULL,
    product_views INT DEFAULT 0,
    product_likes INT DEFAULT 0,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    FOREIGN KEY (category_id) REFERENCES category(category_id) ON DELETE SET NULL
);

--user table

CREATE TABLE user (
    user_id  VARCHAR(255) AUTO_INCREMENT,
    user_name VARCHAR(255) NOT NULL,
    user_email VARCHAR(255) UNIQUE NOT NULL,
    user_password VARCHAR(255) NOT NULL,
    user_phone_number VARCHAR(20) NULL,
    user_address TEXT,
    user_profile_picture VARCHAR(255),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by VARCHAR(255),
    updated_by VARCHAR(255) 
);

--order table

CREATE TABLE product_order (
    product_order_id VARCHAR(255) PRIMARY KEY ,
    buyer_user_id INT NOT NULL,
    product_order_total_price DOUBLE NOT NULL,
    product_order_status ENUM('PENDING', 'SHIPPED', 'DELIVERED', 'CANCELLED', 'CART') DEFAULT 'PENDING',
    product_order_tracking_id VARCHAR(50),
    product_order_shipping_address TEXT NOT NULL,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, 
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    FOREIGN KEY (buyer_id) REFERENCES user(user_id) ON DELETE CASCADE 
);

--order item

CREATE TABLE order_item (
    order_item_id VARCHAR(255) PRIMARY KEY,
    product_order_id VARCHAR(255) PRIMARY KEY,
    product_id VARCHAR(255) PRIMARY KEY,
    quantity INT NOT NULL,
    price DOUBLE NOT NULL,
    order_item_status ENUM('REMOVED', 'ADDED') DEFAULT 'ADDED',
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    FOREIGN KEY (product_order_id) REFERENCES order(product_order_id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES product(product_id) ON DELETE CASCADE
);

--review table

CREATE TABLE review (
    review_id VARCHAR(255),
    user_id VARCHAR(255),
    product_id VARCHAR(255),
    rating DECIMAL(3,2) CHECK (rating BETWEEN 1 AND 5),
    review_description TEXT,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    PRIMARY KEY (review_id, user_id, product_id),
    FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES product(product_id) ON DELETE CASCADE
);


--wishlist

CREATE TABLE wishlist (
    wishlist_id VARCHAR(255),
    user_id VARCHAR(255),
    product_id VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, 
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    PRIMARY KEY (wishlist_id, user_id, product_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES product(product_id) ON DELETE CASCADE
);

CREATE TABLE payment (
    payment_id VARCHAR(255) PRIMARY KEY ,
    product_order_id INT NOT NULL,
    user_id VARCHAR(255) NULL,
    payment_method ENUM('Credit Card', 'Debit Card', 'PayPal', 'UPI', 'COD') NOT NULL,
    payment_status ENUM('Pending', 'Completed', 'Failed', 'Refunded') DEFAULT 'Pending',
    transaction_id VARCHAR(255) UNIQUE,
    amount DECIMAL(10,2) NOT NULL,
    currency VARCHAR(10) DEFAULT 'USD',
    payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    FOREIGN KEY (product_order_id) REFERENCES product_order(product_order_id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE CASCADE
);
