repo https://github.com/gabzafra/DII_P2.1_GABRIEL_ZAFRA_LALLANA

Usuarios precargados

Administrador:
"admin@mail.com","admin"

Usuarios normales:
"adam@mail.com", "aaaa",
"betty@mail.com","bbbb",
"charlie@mail.com","cccc"
"cecil@mail.com","cece"
"diane@mail.com","dddd"
"eric@mail.com", "eeee"

Como ampliación se ha añadido al área de administración un formulario que permite filtrar la lista de usuario según el comienzo de sus nombres.

Tal como se sugería he tomado como punto de partida la práctica 1.1 convirtiéndola a MVC y buena parte de los estilos de la 1.2. He incluido el guardar en sesión el nombre del usuario, para que se muestre en el encabezado. A la hora de realizar cambios en la "BBDD" se tiene en cuenta el id almacenado en sesión para ver si el usuario actual está autorizado (es el propietario o un administrador). En el caso de las contraseñas, solo las puede actualizar el propietario.

Para los mensajes de error he procurado mover la mayor cantidad de la lógica de control de errores al UserService mediante métodos validate que devuelven un mensaje vacío si todo va bien o uno con contenido para que el controlador lo mande a la vista.
