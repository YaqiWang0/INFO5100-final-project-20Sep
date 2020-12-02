package dto;

import dao.Dealer;
import dao.Special;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface AbstractPersistent {
    public List<Dealer> getAllDealers() throws IOException, FileNotFoundException;
    public void writeDealers(List<Dealer> dealers) throws IOException;
    public List<Special> getAllSpecials() throws IOException;
    public void writeSpecials(List<Special> allSpecials) throws IOException;
}
