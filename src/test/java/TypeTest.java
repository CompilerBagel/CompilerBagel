import Type.ArrayType;
import Type.FunctionType;
import Type.PointerType;
import Type.Type;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static Type.FloatType.IRFloatType;
import static Type.Int32Type.IRInt32Type;
import static Type.VoidType.IRVoidType;
import static org.junit.Assert.*;

public class TypeTest {
    private static final Type int32Type = IRInt32Type();
    private static final Type floatType = IRFloatType();
    @Test
    public void arrayTypeTest1() {
        Type array1 = new ArrayType(int32Type, 5);
        List<Integer> elementDimension = new ArrayList<>();
        elementDimension.add(5);
        assertEquals(array1.getText(), "[5 x i32]");
        assertEquals(((ArrayType) array1).getElementDimension(), elementDimension);
    }

    @Test
    public void arrayTypeTest2() {
        Type array1 = new ArrayType(int32Type, 5);
        Type array2 = new ArrayType(array1, 2);
        List<Integer> elementDimension = new ArrayList<>();
        elementDimension.add(2);
        elementDimension.add(5);
        assertEquals(array2.getText(), "[2 x [5 x i32]]");
        assertEquals(((ArrayType) array2).getElementDimension(), elementDimension);
    }

    @Test
    public void arrayTypeTest3() {
        Type array1 = new ArrayType(floatType, 6);
        Type array2 = new ArrayType(array1, 2);
        assertEquals(array2.getText(), "[2 x [6 x float]]");
    }

    @Test
    public void arrayTypeTest4(){
        Type array1 = new ArrayType(int32Type, 2);
        Type array2 = new ArrayType(array1, 3);
        Type array3 = new ArrayType(array2, 4);
        Type array4 = new ArrayType(array3, 5);
        List<Integer> elementDimension = new ArrayList<>();
        elementDimension.add(5);
        elementDimension.add(4);
        elementDimension.add(3);
        elementDimension.add(2);
        assertEquals(((ArrayType)array4).getElementDimension(), elementDimension);
    }

    @Test
    public void functionTypeTest1() {
        List<Type> params = new ArrayList<>();
        params.add(int32Type);
        params.add(floatType);
        FunctionType function1 = new FunctionType(params, int32Type);
        assertEquals(function1.getText(), "(i32, float): i32");
        assertEquals(function1.getParamsTable(), "(i32 %0, float %1)");
    }

    @Test
    public void functionTypeTest2() {
        Type array1 = new ArrayType(int32Type, 5);
        List<Type> params = new ArrayList<>();
        params.add(int32Type);
        params.add(array1);
        Type function1 = new FunctionType(params, IRVoidType());
        assertEquals(function1.getText(), "(i32, [5 x i32])");
    }

    @Test
    public void functionTypeTest3() {
        List<Type> params = new ArrayList<>();
        FunctionType function1 = new FunctionType(params, IRVoidType());
        assertEquals(function1.getText(), "()");
        assertEquals(function1.getParamsTable(), "()");
    }

    @Test
    public void pointerTypeTest1() {
        Type pointer1 = new PointerType(int32Type);
        Type pointer2 = new PointerType(pointer1);
        assertEquals(pointer2.getText(), "i32**");
    }

}
