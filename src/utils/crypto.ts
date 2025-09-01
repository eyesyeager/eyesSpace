import CryptoJS from "crypto-js";

function aesEncrypt(content: string, key: string) {
    try {
        const utf8Key = CryptoJS.enc.Utf8.parse(key);
        const iv = utf8Key;
        const encrypted = CryptoJS.AES.encrypt(
            content,
            utf8Key,
            {
                iv: iv,
                mode: CryptoJS.mode.CBC,
                padding: CryptoJS.pad.Pkcs7
            }
        );
        return encrypted.toString();
    } catch (error) {
        console.error("加密失败:", error);
        return null;
    }
}

function aesDecrypt(content: string, key: string) {
    try {
        const utf8Key = CryptoJS.enc.Utf8.parse(key);
        const iv = utf8Key;
        const decrypted = CryptoJS.AES.decrypt(
            content,
            utf8Key,
            {
                iv: iv,
                mode: CryptoJS.mode.CBC,
                padding: CryptoJS.pad.Pkcs7
            }
        );
        return decrypted.toString(CryptoJS.enc.Utf8);
    } catch (error) {
        console.error("解密失败:", error);
        return null;
    }
}


export { aesEncrypt, aesDecrypt };