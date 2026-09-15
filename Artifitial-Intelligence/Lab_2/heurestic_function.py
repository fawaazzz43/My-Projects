import numpy as np
def to_binary(arr:np.ndarray):
    flat=arr.ravel()
    human=0
    Ai=0
    i=0
    for x in flat:
        if x==1:
            Ai|=(1<<i)
        elif x==-1:
            human|=(1<<i)
        i+=1
    return Ai,human

def number_of_connected_4_binary(human:int,Ai:int):
    rows=(human & (human<<1) & (human<<2) & (human<<3)) & 0x3C78F1E3C78
    col=(human & (human<<7) & (human<<14) & (human<<21)) 
    diagonals=(human & (human<<6) & (human<<12) & (human<<18)) & (0x3C78F<<21)
    diagonals2=(human & (human<<8) & (human<<16) & (human<<24)) & (0x1E3C78<<21)
    rows_double=rows & 0x183060C1830
    rows_5_double=rows & 0x4081020408
    col_double=col & (0x7F << 28)
    diagonals_double= diagonals & (0x6 << 28)
    diagonals_double2= diagonals2 & (0x30 << 28)
    rows_after=((rows_double<<1)&(~human))&(~Ai)
    rows_before=((rows_double>>4)&(~human))&(~Ai)
    col_after=((col_double<<7)&(~human))&(~Ai)
    col_before=((col_double>>28)&(~human))&(~Ai)
    diagonals_after=((diagonals_double<<6)&(~human))&(~Ai)
    diagonals_before=((diagonals_double>>24)&(~human))&(~Ai)
    diagonals_after2=((diagonals_double2<<8)&(~human))&(~Ai)
    diagonals_before2=((diagonals_double2>>32)&(~human))&(~Ai)
    rows_double=(rows_after>>1) & (rows_before<<4)
    col_double=(col_after>>7) & (col_before<<24)
    diagonals_double=(diagonals_after>>6)& (diagonals_before<<24)
    diagonals_double2=(diagonals_after2>>8)&(diagonals_before2<<32)
    number_of_double=rows_double.bit_count()+col_double.bit_count()+diagonals_double.bit_count()+diagonals_double2.bit_count()
    rows_after=((rows_5_double<<1)&(~human))&(~Ai)
    rows_before=((rows_5_double>>4)&(~human))&(~Ai)
    rows_5_double=(rows_after>>1) & (rows_before<<4)
    return rows.bit_count()+col.bit_count()+diagonals.bit_count()+diagonals2.bit_count(),number_of_double+rows_5_double.bit_count()

def number_of_connected_3_binary(Ai:int,human:int):
    rows=(Ai & (Ai<<1) & (Ai<<2)) & 0x3E7CF9F3E7C
    col=Ai & (Ai<<7) & (Ai<<14) 
    diagonals=(Ai & (Ai<<6) & (Ai<<12))  & (0x3E7CF9E<<14)
    diagonals2=(Ai & (Ai<<8) & (Ai<<16))  & (0x1F9F3E3C<<14)
    rows_after=rows & 0x1E3C78F1E3C
    rows_before=rows & 0x3C78F1E3C78
    rows_after=((rows_after<<1)&(~human))&(~Ai)
    rows_before=((rows_before>>3)&(~human))&(~Ai)
    col_after=col & 0x7FFFFC000
    col_before=col & 0x3FFFFE00000
    col_after=((col_after<<7)&(~human))&(~Ai)
    col_before=((col_before>>21)&(~human))&(~Ai)
    diagonals_after=diagonals & (0x78F1E <<14)
    diagonals_before=diagonals & (0x3C78F << 21)
    diagonals_after=((diagonals_after<<6)&(~human))&(~Ai)
    diagonals_before=((diagonals_before>>18)&(~human))&(~Ai)
    diagonals_after2=diagonals2 & (0xF1E3C<<14)
    diagonals_before2=diagonals2 & (0x3E3C78<<21)
    diagonals_after2=((diagonals_after2<<8)&(~human))&(~Ai)
    diagonals_before2=((diagonals_before2>>24)&(~human))&(~Ai)
    rows_double=(rows_after>>1) & (rows_before<<3)
    col_double=(col_after>>7) & (col_before<<21)
    diagonals_double=(diagonals_after>>6)& (diagonals_before<<18)
    diagonals_double2=(diagonals_after2>>8)&(diagonals_before2<<24)
    number_of_double=rows_double.bit_count()+col_double.bit_count()+diagonals_double.bit_count()+diagonals_double2.bit_count()
    number_of_double_in_mid=(rows_double & 0xE1C3870E1C).bit_count()+(col_double & 0xE1C3870E1C).bit_count()+(diagonals_double & 0xE1C3870E1C).bit_count()+(diagonals_double2 & 0xE1C3870E1C).bit_count()
    return rows_before.bit_count()+rows_after.bit_count()+col_after.bit_count()+col_before.bit_count()+diagonals_after.bit_count()+diagonals_before.bit_count()+diagonals_after2.bit_count()+diagonals_before2.bit_count(),number_of_double,number_of_double_in_mid
def number_center(state:int):
    return (state & 0x4081020408).bit_count()