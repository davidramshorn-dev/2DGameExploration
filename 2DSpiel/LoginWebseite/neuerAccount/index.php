<?php
$datenbank = "2DSpiel";
$tabelle = "user";

$conn = new mysqli("localhost", "root", "", $datenbank);

if ($conn->connect_error) {
die("Verbindung fehlgeschlagen: " . $conn->connect_error);
}

session_start();

if (isset($_POST["erstellen"])) {

    $name = $_POST["name"] ?? "";
    $email = $_POST["email"] ?? "";
    $passwort = $_POST["passwort"] ?? "";
    $passwortNr2 = $_POST["passwortNr2"] ?? "";

    if ($email !== "" && $passwort === $passwortNr2) {

        $hash = password_hash($passwort, PASSWORD_DEFAULT);

        // prüfen ob vorhanden
        $stmt = $conn->prepare(
            "SELECT 1 FROM $tabelle WHERE name = ? OR email = ? LIMIT 1"
        );
        $stmt->bind_param("ss", $name, $email);
        $stmt->execute();

        $result = $stmt->get_result();
        $exists = $result->num_rows > 0;

        if (!$exists) {

            $stmt = $conn->prepare(
                "INSERT INTO $tabelle (name, email, passwort) VALUES (?, ?, ?)"
            );
            $stmt->bind_param("sss", $name, $email, $hash);

            if ($stmt->execute()) {
                echo "Account erstellt";
                header("Location: ../index.php");
exit;
            } else {
                echo "Fehler: " . $stmt->error;
            }

        } else {
            echo "Username or email already exist";
        }

    } else {
        echo "Ungültige Email oder Passwort";
    }

}
?>
<!DOCTYPE html>
<html>
    <head>
        <html lang="de">
        <title>
            Login Spiel
        </title>
       <link rel="stylesheet" href="style.css">

    </head>
    <body>
        <div>
            <h1>Bitte geben sie ihre Daten ein</h1>
        </div>
        <form method="post">
        <div>
            Name: <input name="name"><br>
            Email: <input name="email"><br>
            Passwort: <input name="passwort"><br>
            erneut Passwort: <input name="passwortNr2">

        </div>
        <button type="submit" name="erstellen" id="erstellen">Account erstellen</button></form>
        <div>
            <h2>Hast du schon ein Account?</h2>
            <button id="BereitsAccount">Hier clicken</button>
        </div>
        <script src="script.js"></script>

    </body>
</html>