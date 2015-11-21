
<%@attribute name="texto" required="true"%>
<%@attribute name="id" required="true" description="Nome do atributo"%>

<label for="${id}">${texto}</label>
<input type="text" id="${id}" name="${id}"/>
