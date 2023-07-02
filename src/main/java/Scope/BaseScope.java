package Scope;

import IRBuilder.ValueRef;
import Type.Type;

import java.util.LinkedHashMap;
import java.util.Map;

public class BaseScope implements Scope {
    private final Scope enclosingScope;
    private String scopeName;
    private final Map<String, ValueRef> symbols = new LinkedHashMap<>();
    private final Map<String, Type> types = new LinkedHashMap<>();

    public BaseScope(String scopeName, Scope enclosingScope) {
        this.scopeName = scopeName;
        this.enclosingScope = enclosingScope;
    }

    @Override
    public Scope getEnclosingScope() {
        return enclosingScope;
    }

    @Override
    public void define(String name, ValueRef valueRef, Type type) {
        symbols.put(name, valueRef);
        types.put(name, type);
    }

    @Override
    public ValueRef getValueRef(String name) {
        ValueRef valueRef = symbols.get(name);
        if (valueRef != null) {
            return valueRef;
        }

        if (enclosingScope != null) {
            return enclosingScope.getValueRef(name);
        }

        return null;
    }

    @Override
    public Type getType(String name) {
        Type type = types.get(name);
        if (type != null) {
            return type;
        }

        if (enclosingScope != null) {
            return enclosingScope.getType(name);
        }
        return null;
    }

    @Override
    public String getScopeName() {
        return scopeName;
    }
    @Override
    public void setScopeName(String scopeName) {
        this.scopeName = scopeName;
    }
}
