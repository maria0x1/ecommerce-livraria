package br.unitins.tp1.model;

public enum NivelAcesso {
    ROOT(1, "Root"),
    GERENTE(2, "Gerente"),
    OPERACIONAL(3, "Operacional");

    private final int ID;
    private final String LABEL;

    NivelAcesso(int id, String label) {
        this.ID = id;
        this.LABEL = label;
    }

    public static NivelAcesso valueOf(int id) {
        for (NivelAcesso nivel : NivelAcesso.values()) {
            if (nivel.ID == id) {
                return nivel;
            }
        }
        return null;
    }

    public static void imprimirTodos() {
        System.out.println("*** Níveis de Acesso ***");
        for (NivelAcesso nivel : NivelAcesso.values()) {
            System.out.println("[" + nivel.ID + "] - " + nivel.LABEL);
        }
    }

    public int getID() {
        return ID;
    }

    public String getLABEL() {
        return LABEL;
    }
}
