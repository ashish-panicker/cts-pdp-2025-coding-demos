# Spring Security

A filter based security framework. Every request passes through a `FilterChainProxy` whose responsibility is to
delegate these requests to different security filters, example: Authentication Filter, Authorization Filter, CSRF Filter.

![Diagram](https://substackcdn.com/image/fetch/%24s_%21hnmN%21%2Cf_auto%2Cq_auto%3Agood%2Cfl_progressive%3Asteep/https%3A%2F%2Fsubstack-post-media.s3.amazonaws.com%2Fpublic%2Fimages%2F610c2bdd-9f59-4239-85cf-c4aa40434c46_1200x1487.png?utm_source=chatgpt.com)

## Responsibilities

- **Authentication:** Who is the user?
- **Authorization:** What the user is allowed to do?
- **SecurityContext:** Stores the authenticated user's principal and authorities
- **Password Management:** Secure hashing algorithms like `BCrypt`, `Argon2`
- **Protection:** Protection against CSRF, CQRS, Session Management

## Authentication & Authorization

### Authentication

We supply credentials, spring validates them, if valid spring populated the `SecurityContext`

**Classes Involved**
- `UserDetails` - represents the user
- `UserDetailsService` - fetches user info from DB
- `AuthenticationManager` - performs authentication
- `AuthenticationProvider` - validates credentials
- `PasswordEncoder` - hashes password

### Authorization

Once logged in, establish what the user can do.

## JWT - JSON Web Token

JWT is compact, URL-safe, digitally signed token format widely used to securely transmit identity claims between applications.
It is widely used in authentication, authorization and information exchanges across REST Api's and microservices.
JWT is stateless, meaning the server does not keep the data in the memory. Every details is embedded in the token itself.

### **JWT Format**

```text
eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9
.
eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWUsImlhdCI6MTUxNjIzOTAyMn0
.
KMUFsIDTnFmyG3nMiGM6H9FNFUROf3wh7SmqJp-QV30
```

JWT has 3 parts, each part separated by a `.`

#### 1. Header

Describes how the token is signed
```json
{
  "alg": "SHA256",
  "typ": "JWT"
}
```

#### 2. Payload

Contains claims, claims are information about the user or the system.

```json
{
  "sub": "123456",
  "name": "Ashish",
  "roles": ["ADMIN"],
  "exp": 1733675600
}
```

#### 3. Signature

Used to verify the integrity of the token.

```css
HMACSHA256(
    base64UrlEncode(header) + "." + base64UrlEncode(payload),
    SECRET_KEY
)
```

### JWT Claims

Standard claims
- `iss` issuer
- `sub` subject (user)
- `aud` audience
- `exp` expiration
- `iat` issued at
- `nbf` not before

Do not store sensitive information.

### Advantages of JWT

- No Server session storage
- Self contained
- High performant
- Language independent

### Risks of JWT

- Long lived tokens
- Storing in the browsers `LocalStorage`
- Always use  a signature algorithm, either `HS256` or `RS256` [Used for microservices]