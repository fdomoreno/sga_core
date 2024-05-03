package co.edu.usa.talentotech.sga.utils;



import java.nio.charset.StandardCharsets;
import java.rmi.server.UID;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import co.edu.usa.talentotech.sga.utils.constans.Sistema;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

/*
 * Created on May 03, 2024
 * @author fdomoreno
 */

public class AuthTools {

    /**
     * Metodo que encripta una cadena de texto
     * @param cadena
     * @return
     */
    public static String MD5Encoder(String cadena) {
        
        try {
            MessageDigest md = MessageDigest.getInstance(Sistema.ALGORITMO);
            byte[] b = md.digest(cadena.getBytes());
            int size = b.length;
            StringBuffer h = new StringBuffer(size);
            for (int i = 0; i < size; i++) {
                int u = b[i] & 255;
                if (u < 16) {
                    h.append("0" + Integer.toHexString(u));
                } else {
                    h.append(Integer.toHexString(u));
                }
            }
            return h.toString();
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Retorna el usuario y contrasena
     * @param auth
     * @return
     */
    public static String[] obtenerCredencialesBasic(String auth){
        String base64Credentials = auth.substring(Sistema.BASIC.length()).trim();
        byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
        String credentials = new String(credDecoded, StandardCharsets.UTF_8);
        // credentials = username:password
        return credentials.split(Sistema.SEPARADOR, Sistema.LIMIT_BASIC);
    }

    /**
     * Metodo que valida si una cadena de texto es igual a otra
     * @param cadena
     * @param cadenaEncriptado
     * @return
     */
    public static Boolean MD5Valid(String cadena, String cadenaEncriptado) {
        return MD5Encoder(cadena).equals(cadenaEncriptado);
    }
    
    /**
     * Metodo para generar contraseña aleatoria
     * @return
     */
    public static String GenerarContrasena() {
        String cadena = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder contrasena = new StringBuilder();
        for (int x = 0; x < Sistema.TAMANO_CONTRASENA; x++) {
            int indiceAleatorio = (int) (Math.random() * cadena.length());
            contrasena.append(cadena.charAt(indiceAleatorio));
        }
        return contrasena.toString();
    }

    /**
     * Metodo para Generar clientId
     */
    public static String GenerarClientId() {
        // TODO Auto-generated constructor stub
        return MD5Encoder("" + ((int) (Math.random() * 1000000) + 1 + (int) (Math.random() * 1000000) + 1));
    }

    /**
     * Metodo para Generar clientSecret
     */
    public static String GenerarClientSecret() {
        // TODO Auto-generated constructor stub
        return MD5Encoder("" + ((int) (Math.random() * 1000000) + 1 + (int) (Math.random() * 1000000) + 1));
    }

    /**
     * De base64 a string
     */
    public static String Base64ToString(String cadena) {
        // TODO Auto-generated constructor stub
        byte[] decodedBytes = Base64.getDecoder().decode(cadena);
        String decodedString = new String(decodedBytes);
        return decodedString;
    }

    /**
     * De string a base64
     */
    public static String StringToBase64(String cadena) {
        // TODO Auto-generated constructor stub
        return Base64.getEncoder().encodeToString(cadena.getBytes());
    }

    public static Object[] decodeJWT(String jwt, String secret_key, String clientId) {
        CharSequence charJwt = jwt;
        //This line will throw an exception if it is not a signed JWS (as expected)
        try{
            Claims claims = Jwts.parser()
            .verifyWith(getSigningSecretKey(secret_key, clientId))
            .build()
            .parseSignedClaims(charJwt).getPayload();
            return new Object[]{claims.getSubject(), claims.get("clientId").toString(), claims.getIssuedAt(),claims.getExpiration()};
        }catch(JwtException e){
            System.out.println("Error: " + e.getMessage());
            return null;
        }
        //return claims;
    }

    public static String createJWT(String clientId, String issuer, String secret,  String subject, long ttlMillis, String encriptionKey) {

        //The JWT signature algorithm we will be using to sign the token
        return Jwts.builder()
            .header()
            //Generar UUID
            .keyId(new UID().toString())
            .and()
            .audience().add(issuer).and()
            .subject((subject))
            .claim("clientId", clientId)
            .issuedAt(new Date())
            .expiration(new Date((new Date()).getTime() + ttlMillis))
            .signWith(getSigningKey(secret, clientId))
            .compact();
    }

    private static Key getSigningKey(String secret, String clientID) {
        String key = StringToBase64(secret + clientID);
        byte[] keyBytes = Decoders.BASE64.decode(key);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private static Key getSigningKeyAlt(String secret) {
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private static SecretKey getSigningKey() {
        return Jwts.SIG.HS256.key().build();
    }

    public static SecretKey getSigningSecretKey(String secret, String clientId) {
        String key = StringToBase64(secret + clientId);
        byte[] keyBytes = Decoders.BASE64.decode(key);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public static String ObtenerTokenBearer(String token){
        return token.substring(Sistema.POSICION_TOKEN).trim();
    }
}
