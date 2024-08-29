## Environment Variables

To run this project, you will need to add the following environment variables to your run configuration

`DB_URL`

example:

    DB_URL=jdbc:postgresql://localhost:5432/book_store_dev_db

`DB_USERNAME`

example:

    DB_USERNAME=postgres

`DB_PASSWORD`
        
example:

    DB_PASSWORD=your_database_password

## API Reference

Note: Add bearer token for authorized request

`[1] - Authorization Admin`

`[2] - Authorization User`

#### ADMIN

```http
  POST /api/admin/register
```
```http
  POST /api/admin/login
```

#### USER

```http
  POST /api/user/register
```
```http
  POST /api/user/login
```
```http
  PUT /api/user/{id} [2]
```

#### PRODUCT

```http
  POST /api/products [1]
```
```http
  PUT /api/products/{id} [1]
```
```http
  DELETE /api/products/{id} [1]
```
```http
  GET /api/products [1][2]
```
```http
  GET /api/products/{id} [1][2]
```

#### CART

```http
  POST /api/cart [2]
```
```http
  PUT /api/cart/{itemId} [2]
```
```http
  DELETE /api/cart/{itemId} [2]
```
```http
  GET /api/cart/{username} [2]
```

#### FEEDBACK

```http
  POST /api/feedbacks/submit [2]
```
```http
  GET /api/feedbacks/{productId} [2]
```

#### ORDER

```http
  POST /api/orders [2]
```
```http
  GET /api/orders [1]
```
```http
  GET /api/orders/history/{username} [1][2]
```

#### WISHLIST

```http
  POST /api/wishlist [2]
```
```http
  DELETE /api/wishlist/{itemId} [2]
```
```http
  GET /api/wishlist/{username} [2]
```