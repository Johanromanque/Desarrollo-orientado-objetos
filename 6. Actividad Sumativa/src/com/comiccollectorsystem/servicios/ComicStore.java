package com.comiccollectorsystem.servicios;
import com.comiccollectorsystem.modelo.Comic;
import com.comiccollectorsystem.modelo.Usuario;
import com.comiccollectorsystem.excepciones.ComicNoDisponibleException;
import com.comiccollectorsystem.excepciones.UsuarioNoEncontradoException;

import java.util.*;

public class ComicStore {
    private ArrayList<Comic> comics = new ArrayList<>();
    private HashMap<String, Usuario> usuarios = new HashMap<>();

    public void agregarComic(Comic c) {
        comics.add(c);
    }

    public void registrarUsuario(Usuario u) {
        usuarios.put(u.getRut(), u);
    }

    public void comprarComic(String rut, String titulo) throws UsuarioNoEncontradoException, ComicNoDisponibleException {
        Usuario usuario = usuarios.get(rut);
        if (usuario == null) throw new UsuarioNoEncontradoException("Usuario no registrado.");

        for (Comic c : comics) {
            if (c.getTitulo().equalsIgnoreCase(titulo) && c.estaDisponible()) {
                usuario.agregarCompra(c);
                c.marcarPrestado();
                return;
            }
        }
        throw new ComicNoDisponibleException("Comic no disponible.");
    }

    public ArrayList<Comic> getComics() {
        return comics;
    }

    public HashMap<String, Usuario> getUsuarios() {
        return usuarios;
    }
}
