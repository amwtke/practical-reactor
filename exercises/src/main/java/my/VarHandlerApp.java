package my;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

public class VarHandlerApp {
    private static final VarHandle VAR;
    Integer var;

    static {
        try {
            MethodHandles.Lookup l = MethodHandles.lookup();
            VAR = l.findVarHandle(VarHandlerApp.class, "var", Integer.class);
        } catch (ReflectiveOperationException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    final void setVarOpaque(Integer var) {
        VAR.setOpaque(this, var);
    }

    final Integer getVarOpaque() {
        return (Integer) VAR.getOpaque(this);
    }

    final void setRelax(Integer var) {
        VAR.set(this, var);
    }

    final Integer getRelax() {
        return (Integer) VAR.get(this);
    }

    final void setAcquire(Integer var) {
        VAR.set(this, var);
    }

    final Integer getAcquire() {
        return (Integer) VAR.getAcquire(this);
    }

    public static void main(String[] args) {
        VarHandlerApp app = new VarHandlerApp();
        app.setAcquire(1);
        Integer acquire = app.getAcquire();
//        System.out.println(acquire);
    }
}
