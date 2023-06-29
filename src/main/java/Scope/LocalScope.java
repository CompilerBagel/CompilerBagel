package Scope;

public class LocalScope extends BaseScope{
    public LocalScope(String scopeName, Scope enclosingScope) {
        super(scopeName, enclosingScope);
    }
}
