package Scope;

public class GlobalScope extends BaseScope{

    public GlobalScope(String scopeName, Scope enclosingScope) {
        super(scopeName, enclosingScope);
    }
}
