package dev.mayank;

class BasicSpellChecker implements SpellChecker {
    @Override
    public boolean checkSpelling(String msg) {
        if (msg != null && !msg.isEmpty()) {
            System.out.println("Basic spell check is passed.");
            return true;
        }
        System.out.println("Basic spell check failed.");
        return false;
    }
}