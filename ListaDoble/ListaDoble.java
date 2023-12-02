public class ListaDoble {
    Node topForward;
    Node topBackward;

    // Métodos para los casos de inserción de nodos
    public boolean insertaPrimerNodo(String dato) {
        if (topForward == null) { // La lista está vacía
            topForward = new Node();
            topForward.name = dato;
            topForward.previous = null;
            topForward.next = null;

            topBackward = topForward;

            return true;
        } else {
            return false;
        }
    }

    public void imprimir() {
        System.out.print("[X]");

        for (Node temp = this.topForward; temp != null; temp = temp.next) {
            System.out.print(" <- [ " + temp.name + " ] -> ");
        }

        System.out.print("[X]\n");
    }

    public String toString() {
        String cadAux = "[X]";
        for (Node temp = this.topForward; temp != null; temp = temp.next) {
            cadAux += " <- [ " + temp.name + " ] -> ";
        }

        cadAux += "[X]\n";

        return cadAux;
    }

    public void insertaAntesPrimerNodo(String nombre) {
        Node temp;
        temp = new Node();
        temp.name = nombre;
        temp.next = this.topForward;
        this.topForward.previous = temp;
        this.topForward = temp;
        temp = null;
    }

    public void insertaAlFinal(String nombre) {
        Node temp = new Node();
        temp.name = nombre;
        temp.next = null;

        temp.previous = this.topBackward;
        this.topBackward.next = temp;
        this.topBackward = temp;
        temp = null;
    }

    public boolean insertaEntreNodos(String nombre, String buscado) {
        Node temp = new Node();
        temp.name = nombre;
        Node temp2 = this.topForward;

        while ((temp2 != null) && temp2.name.equals(buscado) == false) {
            temp2 = temp2.next;
        }

        if (temp2 != null) { // Nodo buscado se encontró
            temp.next = temp2.next;
            temp2.next = temp;

            temp.previous = temp2;
            temp.next.previous = temp;

            temp = null;
            temp2 = null;

            return true;
        } else return false;
    }

    // Métodos de borrado
    public void borrarPrimerNodo() {
        this.topForward = this.topForward.next;
        this.topForward.previous.next = null;
        this.topForward.previous = null;
    }

    public void borrarUltimoNodo() {
        this.topBackward = this.topBackward.previous;
        this.topBackward.next.previous = null;
        this.topBackward.next = null;
    }

    // Borrar cualquier nodo que no sea el primero
    public boolean borrarCualquierNodo(String buscado) {
        Node temp = this.topForward;

        while ((temp != null) && temp.name.equals(buscado) == false) {
            temp = temp.next;
        }

        if (temp != null) { // Nodo buscado se encontró
            temp.next = temp.next.next;
            temp.next.previous.previous = null;
            temp.next.previous.next = null;
            temp.next.previous = temp;
            temp = null;

            return true;
        } else return false;
    }

    // Nuevos métodos adicionales
    public Node buscarPorPosicion(int posicion) {
        int contador = 0;
        Node temp = this.topForward;

        while (temp != null && contador < posicion) {
            temp = temp.next;
            contador++;
        }

        return temp;
    }

    public void insertarAntesUltimo(String nombre) {
        Node nuevoNodo = new Node();
        nuevoNodo.name = nombre;
        nuevoNodo.next = null;

        if (this.topForward == null) {
            // La lista está vacía, insertar como primer nodo
            this.topForward = nuevoNodo;
            this.topBackward = nuevoNodo;
        } else {
            // Insertar antes del último nodo
            nuevoNodo.previous = this.topBackward;
            this.topBackward.next = nuevoNodo;
            this.topBackward = nuevoNodo;
        }
    }

    public boolean intercambiarNodos(String nodo1, String nodo2) {
        Node temp = this.topForward;
        Node nodoAnterior1 = null;
        Node nodoAnterior2 = null;
        Node nodoActual1 = null;
        Node nodoActual2 = null;

        // Buscar los nodos y sus nodos anteriores
        while (temp != null) {
            if (temp.name.equals(nodo1)) {
                nodoActual1 = temp;
                break;
            }
            nodoAnterior1 = temp;
            temp = temp.next;
        }

        temp = this.topForward; // Reiniciar la búsqueda desde el principio

        while (temp != null) {
            if (temp.name.equals(nodo2)) {
                nodoActual2 = temp;
                break;
            }
            nodoAnterior2 = temp;
            temp = temp.next;
        }

        // Verificar que ambos nodos fueron encontrados
        if (nodoActual1 != null && nodoActual2 != null) {
            // Intercambiar los nodos
            if (nodoAnterior1 != null) {
                nodoAnterior1.next = nodoActual2;
            } else {
                this.topForward = nodoActual2;
            }

            if (nodoAnterior2 != null) {
                nodoAnterior2.next = nodoActual1;
            } else {
                this.topForward = nodoActual1;
            }

            Node tempNext = nodoActual1.next;
            nodoActual1.next = nodoActual2.next;
            nodoActual2.next = tempNext;

            // Actualizar enlaces para lista doble
            if (nodoActual1.next != null) {
                nodoActual1.next.previous = nodoActual1;
            }
            if (nodoActual2.next != null) {
                nodoActual2.next.previous = nodoActual2;
            }

            return true;
        }

        return false; // Al menos uno de los nodos no fue encontrado
    }
}












