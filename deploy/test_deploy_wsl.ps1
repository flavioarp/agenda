# powershell -ExecutionPolicy Bypass -File .\test_tomcat_fedora_wsl.ps1

$hostname = $(wsl -hostname -I).Trim()
$port = "8080"
$URL = "${hostname}:${port}"
start msedge $URL
