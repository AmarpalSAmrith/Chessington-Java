package training.chessington.model.pieces;

import training.chessington.model.Coordinates;

public class PawnPromotion {
    private boolean pawnPromotionRequired;
    private Coordinates pawnPromotionSquare;

    public PawnPromotion(boolean pawnPromotionRequired, Coordinates pawnPromotionSquare) {
        this.pawnPromotionRequired = false;
        this.pawnPromotionSquare = null;
    }

    public boolean isPawnPromotionRequired() {
        return pawnPromotionRequired;
    }

    public Coordinates getPawnPromotionSquare() {
        return pawnPromotionSquare;
    }

    public void setPawnPromotionRequired(boolean pawnPromotionRequired) {
        this.pawnPromotionRequired = pawnPromotionRequired;
    }

    public void setPawnPromotionSquare(Coordinates pawnPromotionSquare) {
        this.pawnPromotionSquare = pawnPromotionSquare;
    }
}
