echo "[Status] Empacotando a aplicação...";
mvn compile war:exploded -o -Dmaven.test.skip=true;
#mvn package -o -Dmaven.test.skip=true;
echo "[Status] Empacotamento finalizado!";