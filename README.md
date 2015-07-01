# MedSave API

Passos para fazer o upload de uma imagem:
  - Adquirir o token de usuário
  - Enviar o json e a imagem como Multipart

### Autenticação:

- **URL**: http://45.55.92.161:8080/medcloud-api/authenticate
- **JSON**: 
```
 {
    "username": <seu_username>,
    "password": <seu_password>
 }
```
- **Retorno Sucesso**:
```
 {
    "token": <seu_token>
 }
```
- **Retorno Erro**:
```
 {
    "error": <mensagem_de_erro>
 }
```


Guarde esse token para poder realizar o upload.

### Upload da Imagem

O servidor está pronto para receber um formulário Multipart/form-data, ou seja, deve-se enviar as propriedades em formato JSON mais a imagem em partes distintas. Segue um exemplo do uso da API via Ajax e Javascript:

```
    var input = document.getElementById('file');
	var file =  input.files[0];
	var properties = {
		type : <tipo_da_imagem>, // Possíveis valores: CT, NM, MRI, US, DS, DM, DCM, CLL, CRDR, DX e DMM.
		iid : <String>,
		creation_date : <data no formato yyyyMMdd>,
		size : <String>,
        cid : <String>,
        user_token: <token recebido anteriormente>
	};

	var fd = new FormData();
	var blob = new Blob([JSON.stringify(properties)],
	    { type: "application/json" });
	fd.append('properties', blob); // atenção para o nome das partes: 'properties'
	fd.append('file', file);       // e 'file' não devem ser alterados
	
	$.ajax({
		url: 'http://45.55.92.161:8080/medcloud-api/image/new',
		method: "POST",
		headers: {
		         "Content-Type": undefined
		         },
		contentType: false,
		data: fd,
		processData: false,
		mimeType: 'multipart/form-data'
	})
```

**Tipos de Imagens**

```
    CT("Tomografia Computadorizada"),
	NM("Medicina Nuclear"),
	MRI("Ressonância Magnética"),
	US("Ultrassom"),
	DS("Angiografia Digital"),
	DM("Microscopia Eletrônica Digital"),
	DCM("Microscopia Digital a Cores"),
	CLL("Imagens de Luz Colorida"),
	CRDR("Radiografia Digital / Computadorizada"),
	DX("Raio-X Digitalizado"),
	DMM("Mamografia Digital");
```

**Retorno**

O retorno será um JSON simples, contendo apenas um boolean 'success' e uma descrição de erro, caso tenha acontecido algum erro.

```
{ 
    "success": <boolean>,
    "error": <mensagem de erro> // só será enviado caso success = false
}
```
