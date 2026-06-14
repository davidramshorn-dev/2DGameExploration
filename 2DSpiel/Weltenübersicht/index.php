<?php
$datenbank = "2DSpiel";
$tabelle = "welten";

$conn = new mysqli("localhost", "root", "", $datenbank);

if ($conn->connect_error) {
die("Verbindung fehlgeschlagen: " . $conn->connect_error);
}

session_start();

/*ausloggen*/
if (isset($_POST["ausloggen"])) {
session_destroy();
    header("Location: ../LoginWebseite/index.php");
}

/*Datensatz umbennen*/
if (isset($_POST["umbenennen"])) {

$name = $_POST["upName"] ?? "";
$deID= $_POST["deID"] ?? "";

$stmt = $conn->prepare("UPDATE $tabelle SET name=? WHERE id=?;");
$stmt->bind_param("si", $name, $deID);
$stmt->execute();
} 

/*Datensatz löschen*/
if (isset($_POST["delete"])) {
$deID= $_POST["deID"] ?? "";
$userID=$_SESSION["userID"] ?? null;

if (!$userID) {
     header("Location: ../LoginWebseite/index.php");
}

$stmt = $conn->prepare("DELETE FROM $tabelle WHERE id = ? AND userID=?");
$stmt->bind_param("ii", $deID, $userID);
$stmt->execute();
} 

/*Account löschen*/
if (isset($_POST["accDelete"])) {
$userID=$_SESSION["userID"] ?? null;

if (!$userID) {
     header("Location: ../LoginWebseite/index.php");
}

$stmt = $conn->prepare("DELETE FROM $tabelle WHERE userID=?");
$stmt->bind_param("i", $userID);
$stmt->execute();

$stmt = $conn->prepare("DELETE FROM user WHERE id=?");
$stmt->bind_param("i", $userID);
$stmt->execute();

session_destroy();
    header("Location: ../LoginWebseite/index.php");


} 
/*Datensatz hinzufügen*/
if (isset($_POST["insert"])) {

$name = $_POST["name"] ?? "";
$seed=random_int(1, 20);
$userID = $_SESSION["userID"] ?? null;

if (!$userID) {
     header("Location: ../LoginWebseite/index.php");
}


$stmt = $conn->prepare("INSERT INTO $tabelle (name, seed, userID) VALUES (?, ?, ?)");
$stmt->bind_param("sii", $name, $seed, $userID);
$stmt->execute();
} 
?>


<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8">
<title>Weltenübersicht</title>

<style>
table{
border-collapse: collapse;
}
td,th{
border:1px solid black;
padding:5px;
}
form{
margin:0;
}
</style>

</head>
<body>

<h1>Weltenübersicht</h1>

<?php
/* ==========================
Tabelle anzeigen
========================== */
$userID = $_SESSION["userID"] ?? null;

$result = $conn->query("SELECT name, seed, id FROM $tabelle WHERE userID=$userID ORDER BY id ASC");

echo "<table>";
echo "<tr>
<th>ID</th>
<th>Name</th>
<th>Seed</th>
<th>Bearbeiten</th>
</tr>";

while ($row = $result->fetch_assoc()) {
echo "<tr>";
echo "<td>" . htmlspecialchars($row["id"]) . "</td>";
echo "<td>" . htmlspecialchars($row["name"]) . "</td>";
echo "<td>" . htmlspecialchars($row["seed"]) . "</td>";
echo "<td>";
echo "<form method='post'>";
echo "<input name='deID' style='display:none' value='" . htmlspecialchars($row["id"]) . "'>
<input type='text' name="."upName"." value=".htmlspecialchars($row["name"]).">
<button type='submit' name='umbenennen'>umbenennen</button>
<button type='submit' name='delete'>löschen</button>
</form>
</td>";
echo "</tr>";
}

echo "</table>";
?>

<h2>Neue Welt</h2>

<form method="post">
<label>Neue Welt:</label>
<input type="text" name="name">
<button type="submit" name="insert">Erstellen</button>
</form>
<form method="post">
<button type="submit" name="ausloggen">ausloggen</button>
</form>
<form method="post">
<button type="submit" name="accDelete">Account löschen</button>
</form>

<?php $conn->close(); ?>

</body>
</html>