package pomocni.storage;

import glavni.app.quotes.Quote;

import java.util.ArrayList;

public class QuoteStorage {
    private ArrayList<Quote> quotes;
    private static QuoteStorage instance = null;
    public static QuoteStorage getInstance() {
        if(instance == null){
            instance = new QuoteStorage();
        }
        return instance;
    }

    private QuoteStorage(){
        quotes = new ArrayList<>();
        initQuotes();
    }
    private void initQuotes(){
        quotes.add( new Quote("John D. Rockefeller Jr.", "The secret of success is to do the common thing uncommonly well."));
        quotes.add( new Quote("Winston S. Churchill", "Success is not final; failure is not fatal: It is the courage to continue that counts."));
        quotes.add( new Quote("Walt Disney", "The way to get started is to quit talking and begin doing."));
        quotes.add( new Quote("Albert Einstein", "Try not to become a man of success. Rather become a man of value."));
        quotes.add( new Quote("Winston Churchill", "Success is walking from failure to failure with no loss of enthusiasm."));
        quotes.add( new Quote("Wayne Gretzky", "You miss 100% of the shots you don't take."));
        quotes.add( new Quote("Rosa Parks", "I have learned over the years that when one's mind is made up, this diminishes fear."));
        quotes.add( new Quote("Ayn Rand","The question isn't who is going to let me; it's who is going to stop me."));
    }

    public ArrayList<Quote> getQuotes() {
        return quotes;
    }
}
