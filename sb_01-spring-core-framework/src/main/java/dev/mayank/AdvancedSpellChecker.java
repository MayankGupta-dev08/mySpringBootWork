package dev.mayank;

class AdvancedSpellChecker implements SpellChecker {
    @Override
    public boolean checkSpelling(String msg) {
        if (msg != null && !msg.isEmpty()) {
            //Logic: Determines the language, auto-fixes grammar and spelling errors
            System.out.println("Advanced spell check is passed.");
            return true;
        }
        System.out.println("Advanced spell check failed.");
        return false;
    }
}