package v5;

/**
 * La classe Pair<A, B> représente une paire d'objets génériques A et B.
 * Elle permet de stocker deux valeurs et d'y accéder via les méthodes appropriées.
 *
 * @param <A> Type du premier élément de la paire.
 * @param <B> Type du deuxième élément de la paire.
 */
public class Pair<A, B> {
    private final A first;
    private final B second;

    /**
     * Constructeur de la classe Pair.
     *
     * @param first  Premier élément de la paire.
     * @param second Deuxième élément de la paire.
     */
    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Obtient le premier élément de la paire.
     *
     * @return Premier élément de la paire.
     */
    public A getFirst() {
        return first;
    }

    /**
     * Obtient le deuxième élément de la paire.
     *
     * @return Deuxième élément de la paire.
     */
    public B getSecond() {
        return second;
    }
}
