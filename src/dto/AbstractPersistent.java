package dto;

import dao.Dealer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface AbstractPersistent {
    public List<Dealer> getAllDealers() throws IOException, FileNotFoundException;
    public void writeDealers(List<Dealer> dealers) throws IOException
}
