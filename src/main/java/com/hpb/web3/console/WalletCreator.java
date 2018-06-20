package com.hpb.web3.console;

import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import com.hpb.web3.codegen.Console;
import com.hpb.web3.crypto.CipherException;
import com.hpb.web3.crypto.WalletUtils;


public class WalletCreator extends WalletManager {

    public WalletCreator() {
    }

    public WalletCreator(IODevice console) {
        super(console);
    }

    public static void main(String[] args) {
        new WalletCreator().run();
    }

    static void main(IODevice console) {
        new WalletCreator(console).run();
    }

    private void run() {
        String password = getPassword("Please enter a wallet file password: ");
        String destinationDir = getDestinationDir();
        File destination = createDir(destinationDir);

        try {
            String walletFileName = WalletUtils.generateFullNewWalletFile(password, destination);
            console.printf("Wallet file " + walletFileName
                    + " successfully created in: " + destinationDir + "\n");
        } catch (CipherException | IOException | InvalidAlgorithmParameterException
                | NoSuchAlgorithmException | NoSuchProviderException e) {
            Console.exitError(e);
        }
    }
}
