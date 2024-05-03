/* global use, db */
// MongoDB Playground
// Use Ctrl+Space inside a snippet or a string literal to trigger completions.

const database = 'sga';
use(database);

const usuario_collection = 'usuario';
const rol_collection = 'rol';
const permiso_collection = 'permiso';
const estado_collection = 'estado';
const rol_permiso_collection = 'rol_permiso';

// Eliminar colecciones si existen.
db[usuario_collection].drop();
db[rol_collection].drop();
db[permiso_collection].drop();
db[estado_collection].drop();
db[rol_permiso_collection].drop();

// Create a new collection.
db.createCollection(usuario_collection);
db.createCollection(rol_collection);
db.createCollection(permiso_collection);
db.createCollection(estado_collection);
db.createCollection(rol_permiso_collection);

// Insert documents to the collection.
db[usuario_collection].insertMany([
  {
    id : 1,
    nombre : 'Luis Fernando Moreno',
    usuario : 'admin',
    contrasena : 'Prueba12345',
    correo: 'luis.moreno.talentotech@usa.edu.co',
    clientId : '8lC3opfpNq88fAnff6tiRP68WTsa',
    clientSecret : 'uW4GJ9oFtTsyrHpb3J3Efxe103Ya',
    encriptionKey : 'A1B2C3D4E5F6G7H8',
    roles : [
      {
        id : 1,
        nombre : 'Administrador',
        descripcion : 'Administrador del sistema',
      }
    ],
    estado : {
      id : 1,
      descripcion : 'Activo'
    }
  }
]);

db[rol_collection].insertMany([
  {
    id : 1,
    nombre : 'Administrador',
    descripcion : 'Administrador del sistema',
    permisos : [
      {
        id : 1,
        modulo : 'Usuarios',
        descripcion : 'Crear',
        estado : {
          id : 1,
          descripcion : 'Activo'
        }
      },
      {
        id : 2,
        modulo : 'Usuarios',
        descripcion : 'Actualizar',
        estado : {
          id : 1,
          descripcion : 'Activo'
        }
      },
      {
        id : 3,
        modulo : 'Usuarios',
        descripcion : 'Eliminar',
        estado : {
          id : 1,
          descripcion : 'Activo'
        }
      }
    ],
    estado : {
      id : 1,
      nombre : 'Activo',
      descripcion : 'Activo',
    }
  }
]);

db[permiso_collection].insertMany([
  {
    id : 1,
    nombre : 'Crear',
    descripcion : 'Crear registros',
    estado : 1
  },
  {
    id : 2,
    nombre : 'Actualizar',
    descripcion : 'Actualizar registros',
    estado : 1
  },
  {
    id : 3,
    nombre : 'Eliminar',
    descripcion : 'Eliminar registros',
    estado : 1
  }
]);

db[estado_collection].insertMany([
  {
    id : 1,
    nombre : 'Activo',
    descripcion : 'Activo',
  },
  {
    id : 2,
    nombre : 'Inactivo',
    descripcion : 'Inactivo',
  }
]);

db[rol_permiso_collection].insertMany([
  {
    id : 1,
    id_rol : 1,
    id_permiso : 1
  },
  {
    id : 2,
    id_rol : 1,
    id_permiso : 2
  },
  {
    id : 3,
    id_rol : 1,
    id_permiso : 3
  },
  {
    id : 4,
    id_rol : 2,
    id_permiso : 1
  }
]);