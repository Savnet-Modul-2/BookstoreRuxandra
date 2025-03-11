package bookstore.application.enums;

public enum ReservationStatus {
    PENDING {
        @Override
        public Boolean isNextStatePossible(ReservationStatus status) {
            return status == IN_PROGRESS || status == CANCELED;
        }
    },
    IN_PROGRESS {
        @Override
        public Boolean isNextStatePossible(ReservationStatus status) {
            return status == FINISHED || status == DELAYED;
        }
    },
    DELAYED {
        @Override
        public Boolean isNextStatePossible(ReservationStatus status) {
            return status == FINISHED;
        }
    },
    FINISHED {
        @Override
        public Boolean isNextStatePossible(ReservationStatus status) {
            return false;
        }
    },
    CANCELED {
        @Override
        public Boolean isNextStatePossible(ReservationStatus status) {
            return false;
        }
    };

    public abstract Boolean isNextStatePossible(ReservationStatus status);
}
