# Postman Practice

Base URL: http://localhost:8080

## 1. Get all products
GET /api/products

## 2. Get product by ID
GET /api/products/1

## 3. Create product
POST /api/products
Content-Type: application/json

{
  "name": "Laptop",
  "description": "QA practice laptop",
  "price": 55000.00,
  "quantity": 5,
  "category": "ELEC"
}

## 4. Invalid product - boundary
{
  "name": "PC",
  "description": "Invalid 2 character name",
  "price": 0.00,
  "quantity": -1,
  "category": "elec"
}

## 5. Update product
PUT /api/products/1

{
  "name": "Updated Laptop",
  "description": "Updated from Postman",
  "price": 60000.00,
  "quantity": 8,
  "category": "ELEC"
}

## 6. Delete product
DELETE /api/products/1

## 7. Search
GET /api/products/search/name?q=laptop
GET /api/products/search/category?q=ELEC

## What to inspect in Postman
- Status code
- Response body
- Headers
- Response time
- JSON structure
- Error message
- Consistency across repeated requests
