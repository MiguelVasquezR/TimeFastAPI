package utilidades;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Date;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class Utilidades {

    public static Boolean enviarCorreo(String recipientEmail, String recoveryLink, String nombre) {
        String host = "smtp.gmail.com";
        String email = "mvrosas01@gmail.com";
        String password = "xskq lfoq yhau wzya";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("Recuperación de Contraseña");

            String htmlContent = loadHtmlTemplate(nombre);
            String urlRecuperacion = "http://localhost:3000/olvide-password/" + recoveryLink;
            htmlContent = htmlContent.replace("{{enlace_de_recuperacion}}", urlRecuperacion);
            message.setContent(htmlContent, "text/html; charset=utf-8");
            Transport.send(message);
            System.out.println("Correo de recuperación enviado exitosamente.");
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }

    }

    public static String generarToken(Integer idColaborador) {
        try {
            String secret = "a9k3L5m8P2q7T1v4X6y9B0z3N7d5W2g8";
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String token = JWT.create()
                    .withClaim("idColaborador", idColaborador)
                    .sign(algorithm);
            System.out.println("Token JWT: " + token);
            return token;
        } catch (Exception e) {
            System.out.println("erro");
            e.printStackTrace();
            return null;
        }
    }

    public static String obtenerIdColaborador(String token) {
        try {
            String secret = "a9k3L5m8P2q7T1v4X6y9B0z3N7d5W2g8";
            Algorithm algorithm = Algorithm.HMAC256(secret);
            DecodedJWT decodedJWT = JWT.require(algorithm)
                    .build()
                    .verify(token);

            Integer idColaborador = decodedJWT.getClaim("idColaborador").asInt();
            return idColaborador.toString();
        } catch (Exception e) {
            System.out.println("Error al obtener el id del colaborador.");
            e.printStackTrace();
            return null;
        }
    }

    private static String loadHtmlTemplate(String nombre) {

        return "<!DOCTYPE html>\n"
                + "<html lang=\"es\">\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <title>Recuperación de Contraseña</title>\n"
                + "    <style>\n"
                + "        body {\n"
                + "            font-family: Arial, sans-serif;\n"
                + "            background-color: #f4f4f4;\n"
                + "            margin: 0;\n"
                + "            padding: 0;\n"
                + "        }\n"
                + "        .container {\n"
                + "            width: 100%;\n"
                + "            max-width: 600px;\n"
                + "            margin: 0 auto;\n"
                + "            background-color: #ffffff;\n"
                + "            padding: 20px;\n"
                + "            border-radius: 8px;\n"
                + "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n"
                + "        }\n"
                + "        .header {\n"
                + "            text-align: center;\n"
                + "            padding: 10px 0;\n"
                + "        }\n"
                + "        .header img {\n"
                + "            width: 100px;\n"
                + "        }\n"
                + "        .content {\n"
                + "            margin-top: 20px;\n"
                + "            line-height: 1.6;\n"
                + "        }\n"
                + "        .button {\n"
                + "            display: block;\n"
                + "            width: 200px;\n"
                + "            margin: 20px auto;\n"
                + "            padding: 10px;\n"
                + "            text-align: center;\n"
                + "            background-color: #007bff;\n"
                + "            color: #ffffff;\n"
                + "            text-decoration: none;\n"
                + "            border-radius: 5px;\n"
                + "        }\n"
                + "        .footer {\n"
                + "            text-align: center;\n"
                + "            margin-top: 30px;\n"
                + "            font-size: 12px;\n"
                + "            color: #777777;\n"
                + "        }\n"
                + "    </style>\n"
                + "</head>\n"
                + "<body>\n"
                + "    <div class=\"container\">\n"
                + "        <div class=\"header\">\n"
                + "            <img src=\"https://tu-dominio.com/logo.png\" alt=\"Logo de tu empresa\">\n"
                + "        </div>\n"
                + "        <div class=\"content\">\n"
                + "            <h2>Recuperación de Contraseña</h2>\n"
                + "            <p>Hola, " + nombre + "</p>\n"
                + "            <p>Hemos recibido una solicitud para restablecer la contraseña de tu cuenta. Si no realizaste esta solicitud, puedes ignorar este correo electrónico.</p>\n"
                + "            <p>Para restablecer tu contraseña, haz clic en el siguiente botón:</p>\n"
                + "            <a href=\"{{enlace_de_recuperacion}}\" class=\"button\">Restablecer Contraseña</a>\n"
                + "            <p>Si el botón anterior no funciona, copia y pega el siguiente enlace en tu navegador:</p>\n"
                + "            <p><a href=\"{{enlace_de_recuperacion}}\">{{enlace_de_recuperacion}}</a></p>\n"
                + "            <p>Este enlace es válido por 24 horas.</p>\n"
                + "            <p>Gracias,</p>\n"
                + "            <p>El equipo de TimeFast</p>\n"
                + "        </div>\n"
                + "        <div class=\"footer\">\n"
                + "            <p>&copy; 2025 [Nombre de tu empresa]. Todos los derechos reservados.</p>\n"
                + "        </div>\n"
                + "    </div>\n"
                + "</body>\n"
                + "</html>";

    }

}
