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

`[1] - Authorization ROLE_ADMIN`

`[2] - Authorization ROLE_USER`

#### ADMIN

```http
  POST /api/admin/register
  POST /api/admin/login
```

#### USER

```http
  POST /api/user/register
  POST /api/user/login
  PUT /users/{id} <sup>[2]</sup>
```

#### PRODUCT

```http
  POST /api/products <sup>[1]</sup>
  PUT /api/products/{id} <sup>[1]</sup>
  DELETE /api/products/{id} <sup>[1]</sup>
  GET /api/products <sup>[1][2]</sup>
  GET /api/products/{id} <sup>[1][2]</sup>
```

#### CART

```http
  POST /api/cart <sup>[2]</sup>
  PUT /api/cart/{itemId} <sup>[2]</sup>
  DELETE /api/cart/{itemId} <sup>[2]</sup>
  GET /api/cart/{username} <sup>[2]</sup>
```

#### FEEDBACK

```http
  POST /api/feedbacks/submit <sup>[2]</sup>
  GET /api/feedbacks/{productId} <sup>[2]</sup>
```

#### ORDER

```http
  POST /api/orders <sup>[2]</sup>
  GET /api/orders <sup>[1]</sup>
  GET /api/orders/history/{username} <sup>[1][2]</sup>
```

#### WISHLIST

```http
  POST /api/wishlist <sup>[2]</sup>
  DELETE /api/wishlist/{itemId} <sup>[2]</sup>
  GET /api/wishlist/{username} <sup>[2]</sup>
```