echo "[Status] Empacotando a aplicação...";
#mvn compile war:exploded -o -Dmaven.test.skip=true;
mvn compile war:exploded -Dmaven.test.skip=true;
echo "[Status] Empacotamento finalizado!";