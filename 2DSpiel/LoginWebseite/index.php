<?php
$datenbank = "2DSpiel";
$tabelle = "user";

$conn = new mysqli("localhost", "root", "", $datenbank);

if ($conn->connect_error) {
die("Verbindung fehlgeschlagen: " . $conn->connect_error);
}

session_start();

// /*Datensatz löschen*/
// if (isset($_POST["delete"])) {
// $id = $_POST["id"];

// $stmt = $conn->prepare("DELETE FROM $tabelle WHERE Posten = ?");
// $stmt->bind_param("s", $posten);
// $stmt->execute();
// }

/* Datensatz überprüfen */
if (isset($_POST["einloggen"])) {

    $passwortEingabe = $_POST["passwort"];
    $name = $_POST["name"];
    $email = $_POST["email"];

   $stmt = $conn->prepare(
            "SELECT id, passwort FROM $tabelle WHERE name = ? OR email = ? LIMIT 1"
        );
        $stmt->bind_param("ss", $name, $email);
        $stmt->execute();
        
$result = $stmt->get_result();
$row = $result->fetch_assoc();
if ($row && password_verify($passwortEingabe, $row["passwort"])) {
    echo "Login erfolgreich";
    $_SESSION["userID"] = $row["id"];
    header("Location: ../Weltenübersicht/index.php");
exit;
} else {
    echo "Login fehlgeschlagen";
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
            <h1>Bitte loggen sie sich ein</h1>
        </div><form method="post">
        <div>
            Name: <input name="name"><br>
            Email: <input name="email"><br>
            Passwort: <input name="passwort">

        </div>
        <button type="submit" name="einloggen" id="einloggen">Einloggen</button></form>
        <div>
            <h2>Noch kein Account?</h2>
            <button id="AccountErstellen">Hier clicken</button>
        </div>
        <script src="script.js"></script>
    </body>
</html>