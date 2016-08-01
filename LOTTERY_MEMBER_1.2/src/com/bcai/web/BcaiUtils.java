package com.bcai.web;

public class BcaiUtils {
	
	public static final long BTCAMOUNT_ID = 1;
	
    public static final String LOGINACCOUNT = "LOGINACCOUNT";
    
    public static final double FEE = 0.1;
    
    public static final double sellBonus = 0.1;
	


	public static final String ADMIN_ACCESS_ROLE = "backstage";
	/**
	 * 登陆账户
	 */		
	
    
     public static final String LOGIN_ROLE = "LOGIN_ROLE";
	
	public static final String ACCESS_ROLE = "USER_LOGIN";
	
	
	
	public static final int BN = 1600;
    
	public enum MANAGE{
		/**
		 * 2表示铜级别
		 */
		CLASS_A{
        
			@Override
			public double getName() {
				// TODO Auto-generated method stub
				return 0.03;
			}
			
		},
		/**
		 * 银卡
		 */
		CLASS_B{
			@Override
			public double getName() {				
				return 0.02;
			}
			
		}
		,
		/**
		 * 金卡会员
		 */
		CLASS_C{
			@Override
			public double getName() {				
				return 0.01;
			}
			
		},
		CLASS_D{//钻卡会员
			@Override
			public double getName() {				
				return 0.005;
			}
			
		},
		CLASS_E{//钻卡会员
			@Override
			public double getName() {				
				return 0.0025;
			}
			
		},
		CLASS_F{//钻卡会员
			@Override
			public double getName() {				
				return 0.0025;
			}
			
		}
		
		;
		public abstract double getName();
	 }
	
	
	public enum DEALBONUS{
		/**
		 * 2表示铜级别
		 */
		CLASS_A{
        
			@Override
			public Double getName() {
				// TODO Auto-generated method stub
				return 0.08;
			}
			
		},
		/**
		 * 银卡
		 */
		CLASS_B{
			@Override
			public Double getName() {				
				return 0.09;
			}
			
		}
		,
		/**
		 * 金卡会员
		 */
		CLASS_C{
			@Override
			public Double getName() {				
				return 0.10;
			}
			
		},
		CLASS_D{//钻卡会员
			@Override
			public Double getName() {				
				return 0.12;
			}
			
		}
		
		;
		public abstract Double getName();
	 }
	
	
}
