--database
CREATE SCHEMA craft-verse;
USE craft-verse;
--table
--category table

CREATE TABLE category (
    category_id INT AUTO_INCREMENT PRIMARY KEY,      
    category_title VARCHAR(100) NOT NULL,                     
    category_description TEXT, 
    category_image_url VARCHAR(255),                         
    category_status ENUM('Active', 'Inactive') DEFAULT 'Active',
    created_by INT,                                 
    updated_by INT,                                 
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, 
    
);