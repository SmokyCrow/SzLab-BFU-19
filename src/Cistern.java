public class Cistern extends ActiveElement{
    /**
     * Az objektumok típusának kiírásához használt felüldefiniált függvény
     * @return az objektum típusa
     */
    @Override
    public String toString() { return "cistern";}

    /**
     * Visszaadja, hogy sikeresen fel lehet-e venni egy új csövet vagy sem.
     * @param e az ellenőrizendő mező
     * @return mindig true a skeletonban
     */
    public boolean giveElementEnd(Element e){
        return true;
    }

    /**
     * Beköt egy csövet a ciszternába. miután ellenőrizte, hogy az adott cső nincs-e már bekötve.
     * @param e a ciszternába bekötendő cső mező elem.
     * @return mindig true a skeletonbaqn.
     */
    public boolean connectElement(Element e){
        return true;
    }

}
