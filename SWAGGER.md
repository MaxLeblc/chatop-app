# 📚 Guide Swagger - Documentation Interactive de l'API

## ✅ Swagger est maintenant configuré !

Swagger (aussi appelé OpenAPI) génère une **documentation interactive** de votre API automatiquement.

---

## 🌐 Accéder à Swagger UI

Une fois le serveur Spring Boot démarré :

```bash
cd backend
./mvnw spring-boot:run
```

Ouvrez votre navigateur et allez sur :

👉 **http://localhost:8080/swagger-ui.html**

Vous verrez une interface graphique avec tous vos endpoints !

---

## 🎯 Ce que Swagger vous permet de faire

### 1️⃣ **Voir tous vos endpoints**
- `POST /api/auth/login` - Connexion
- `POST /api/auth/register` - Inscription
- `GET /api/user/{id}` - Récupérer un utilisateur
- `GET /api/user/me` - Récupérer l'utilisateur connecté

### 2️⃣ **Tester l'API directement depuis le navigateur**
Plus besoin de Postman ! Vous pouvez :
- Cliquer sur un endpoint
- Cliquer sur "Try it out"
- Remplir les paramètres
- Cliquer sur "Execute"
- Voir la réponse

### 3️⃣ **Voir les modèles de données (DTOs)**
Swagger affiche automatiquement :
- Les champs requis (`email`, `password`)
- Les types de données (`string`, `integer`)
- Les validations (`@NotBlank`, `@Email`, `@Size`)

### 4️⃣ **Tester avec authentification JWT**
1. Faites d'abord un `POST /api/auth/login`
2. Copiez le token JWT de la réponse
3. Cliquez sur le bouton **"Authorize"** (🔒 en haut à droite)
4. Collez le token dans le champ
5. Maintenant vous pouvez tester les endpoints protégés !

---

## 🔧 Comment ça marche ?

### **Dépendance Maven ajoutée :**
```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.6.0</version>
</dependency>
```

### **Configuration créée :**
`OpenApiConfig.java` - Configure les infos de l'API et l'authentification JWT

### **Annotations ajoutées dans les Controllers :**

#### **Sur la classe :**
```java
@Tag(name = "Authentication", description = "Endpoints d'authentification")
```
→ Crée une section "Authentication" dans Swagger

#### **Sur les méthodes :**
```java
@Operation(summary = "Connexion utilisateur", description = "Authentifie un utilisateur...")
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Connexion réussie"),
    @ApiResponse(responseCode = "401", description = "Email ou mot de passe incorrect")
})
```
→ Documente l'endpoint avec un résumé et les codes de réponse possibles

#### **Pour les endpoints protégés :**
```java
@SecurityRequirement(name = "Bearer Authentication")
```
→ Indique que cet endpoint nécessite un token JWT

---

## 📖 URLs importantes

| URL | Description |
|-----|-------------|
| http://localhost:8080/swagger-ui.html | Interface graphique interactive |
| http://localhost:8080/v3/api-docs | Spécification OpenAPI au format JSON |
| http://localhost:8080/v3/api-docs.yaml | Spécification OpenAPI au format YAML |

---

## 🧪 Exemple d'utilisation pas à pas

### **Étape 1 : Démarrer le serveur**
```bash
cd backend
./mvnw spring-boot:run
```

### **Étape 2 : Ouvrir Swagger UI**
Ouvrez http://localhost:8080/swagger-ui.html dans votre navigateur

### **Étape 3 : Créer un compte**
1. Dépliez la section **"Authentication"**
2. Cliquez sur **POST /api/auth/register**
3. Cliquez sur **"Try it out"**
4. Remplissez le Request body :
```json
{
  "email": "test@example.com",
  "name": "Test User",
  "password": "password123"
}
```
5. Cliquez sur **"Execute"**
6. Vous recevez un token JWT :
```json
{
  "token": "eyJhbGciOiJIUzUxMiJ9..."
}
```

### **Étape 4 : S'authentifier dans Swagger**
1. Copiez le token (sans les guillemets)
2. Cliquez sur le bouton **"Authorize"** 🔒 en haut à droite
3. Collez le token dans le champ
4. Cliquez sur **"Authorize"** puis **"Close"**

### **Étape 5 : Tester un endpoint protégé**
1. Dépliez la section **"Users"**
2. Cliquez sur **GET /api/user/1**
3. Cliquez sur **"Try it out"**
4. Laissez `id = 1`
5. Cliquez sur **"Execute"**
6. Vous recevez les données de l'utilisateur :
```json
{
  "id": 1,
  "email": "test@example.com",
  "name": "Test User",
  "createdAt": "2025-11-15T16:19:55",
  "updatedAt": "2025-11-15T16:19:55"
}
```

---

## 🆚 Swagger vs Postman

| Critère | Swagger | Postman |
|---------|---------|---------|
| **Intégration** | Auto-généré depuis le code | Collection manuelle |
| **Mise à jour** | Automatique quand le code change | Manuelle |
| **Accessibilité** | Navigateur web (aucune installation) | Application à installer |
| **Partage** | URL à partager | Exporter/importer collection |
| **Documentation** | Toujours à jour avec le code | Peut être obsolète |
| **Tests avancés** | Limité | Tests scriptés, variables d'env |

**Conseil :** Utilisez Swagger pour la **documentation** et Postman pour les **tests complexes**.

---

## 🔒 Sécurité

Les endpoints Swagger sont configurés comme **publics** dans `SecurityConfig.java` :

```java
.requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
```

**⚠️ EN PRODUCTION :** Vous devriez :
- Soit désactiver Swagger complètement
- Soit protéger ces URLs avec une authentification Basic

---

## 💡 Bonnes pratiques

### ✅ À FAIRE :
- Ajouter des descriptions claires dans `@Operation`
- Documenter tous les codes de réponse possibles (`200`, `400`, `401`, `404`)
- Utiliser `@Schema` pour décrire les champs des DTOs
- Grouper les endpoints par tag (`@Tag`)

### ❌ À NE PAS FAIRE :
- Laisser Swagger activé en production sans protection
- Oublier de documenter les nouveaux endpoints
- Mettre des informations sensibles dans les descriptions

---

## 🎓 Pour aller plus loin

Documentation officielle SpringDoc :
- https://springdoc.org/

Spécification OpenAPI :
- https://swagger.io/specification/

---

**Votre API est maintenant parfaitement documentée ! 🚀**
