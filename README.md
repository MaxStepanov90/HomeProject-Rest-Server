1.Необходимо зайти от имени администратора и ввести в терминале keytool команду:

keytool -genkeypair -alias ds -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore ds.p12 -validity 4000

Будет предложено ответить на вопросы.
При ответе на вопрос: What is your first and last name?
необходимо написать ([Unknown]:  localhost). На остальные вопросы отвечать самостоятельно.                 

2.Создать временный файл сертификата.

Пароль: password;

keytool -export -keystore ds.p12 -alias ds -file dsCertificate.cer

,где dsCertificate.cer - название сертификата.

3.импортировать сертификат в ваше хранилище ключей

Пароль: changeit;

keytool -importcert -file dsCertificate.cer -keypass password -keystore "C:/Program Files/Java/jdk-13.0.2/lib/security/cacerts"
