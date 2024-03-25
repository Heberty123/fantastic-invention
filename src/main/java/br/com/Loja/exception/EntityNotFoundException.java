package br.com.Loja.exception;

public class EntityNotFoundException extends RuntimeException {

    public final Integer codeError = 404;
    
    public EntityNotFoundException(String entityType, Long entityId) {
        super("Entidade " + entityType + " com id: " + entityId+ " não foi encontrada!");
    }
}
