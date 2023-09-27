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
        VAR.setRelease(this, var);
    }

    final Integer getAcquire() {
        return (Integer) VAR.getAcquire(this);
    }

    public static void main(String[] args) {
//        System.out.println("o");
        VarHandlerApp app = new VarHandlerApp();
        app.setAcquire(1);
//        Integer acquire = app.getVarOpaque();
        Object acquire1 = VAR.getAcquire(app);
        System.out.println(acquire1);

        Thread t = new Thread(() -> {
            try {
                for (int i = 0; i < 3; i++) {
                    Thread.sleep(1000);
                    System.out.println("xiaojin");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        t.setName("xiaojin-thread");
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
